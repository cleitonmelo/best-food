package com.fiap.techchalleng.best_food.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ApiPerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    ActionBuilder criarReservaRequest = http("criar reserva")
            .post("/api/v1/reservas")
            .body(StringBody(session -> {
                String randomDate = RandomDateTimeGenerator.generateRandomDate();
                String randomTime = RandomDateTimeGenerator.generateRandomTime();
                return "{ " +
                        "\"idRestaurante\": \"65b1bbee-c784-4457-be6d-d00b0be5c9e0\"," +
                        "\"idMesa\": \"592ac344-9f12-40cd-8ed9-1fde6ad9006e\"," +
                        "\"nome\": \"nome\"," +
                        "\"telefone\": \"(11) 99999-9999\"," +
                        "\"qtdeLugares\": \"5\"," +
                        "\"dataReserva\": \"" + randomDate + "\"," +
                        "\"horaReserva\": \"" + randomTime + "\"" +
                        "}";
            }))
            .check(status().is(200))
            .check(jsonPath("$.id").saveAs("reservaId"))
            .check(bodyString().saveAs("responseBody"));

    ActionBuilder cancelarReservaRequest = http("cancelar reserva")
            .put("/api/v1/reservas/#{reservaId}")
            .check(status().is(200));

    ScenarioBuilder cenarioCriarReserva = scenario("criar reserva")
            .exec(criarReservaRequest);

    ScenarioBuilder cenarioCriarCancelarReserva = scenario("criar e cancelar reserva")
            .exec(criarReservaRequest)
            //.exec(session -> {
            //    System.out.println(session.getString("reservaId"));
            //    return session;
            //})
            .exec(cancelarReservaRequest);

   ActionBuilder criarComentarioRequest = http("criar reserva")
            .post("/api/v1/comentarios")
            .body(StringBody(session -> {
                String randomDate = RandomDateTimeGenerator.generateRandomDate();
                String randomTime = RandomDateTimeGenerator.generateRandomTime();
                return "{ " +
                        "\"idReserva\": \"592ac344-9f12-40cd-8ed9-1fde6ad9006e\"," +
                        "\"comentario\": \"Novo comentario\"," +
                        "\"dataComentario\": \"" + randomDate + "\"," +
                        "\"horaComentario\": \"" + randomTime + "\"" +
                        "}";
            }))
            .check(status().is(200))
            .check(jsonPath("$.id").saveAs("comentarioId"))
            .check(bodyString().saveAs("responseBody"));
    

   ScenarioBuilder cenarioCriarComentario = scenario("criar comentario")
           .exec(criarComentarioRequest);

   
    {
        setUp(
                cenarioCriarReserva.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))),
                cenarioCriarCancelarReserva.injectOpen(
                        rampUsersPerSec(1)
                                .to(30)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(30)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(30)
                                .to(1)
                                .during(Duration.ofSeconds(10))),
                cenarioCriarComentario.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10)))
                
        		)
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(50),
                        global().failedRequests().count().is(0L));
    }
}
