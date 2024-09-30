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

    ActionBuilder criarRestauranteRequest = http("criar restaurante")
            .post("/api/v1/restaurantes")
            .body(StringBody(session -> {
                return "{\n" +
                        "    \"nome\": \"Restaurante de TESTE\",\n" +
                        "    \"tipoCozinha\": \"BRASILEIRA\",\n" +
                        "    \"mesas\": [\n" +
                        "        {\n" +
                        "            \"codigo\" : 1,\n" +
                        "            \"lugares\" : 4\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"codigo\" : 2,\n" +
                        "            \"lugares\" : 4\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"codigo\" : 3,\n" +
                        "            \"lugares\" : 4\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
            }))
            .check(status().is(200))
            .check(jsonPath("$.nome").saveAs("nome"))
            .check(jsonPath("$.tipoCozinha").saveAs("tipoCozinha"))
            .check(bodyString().saveAs("responseBody"));

    ScenarioBuilder cenarioCadastrarRestaurante = scenario("criar restaurante")
            .exec(criarRestauranteRequest);

    ActionBuilder buscarRestauranteRequest = http("buscar restaurante")
            .get("/api/v1/restaurantes")
            .check(status().is(200));

    ScenarioBuilder cenarioBuscarRestaurantes = scenario("buscar Restaurantes")
            .exec(buscarRestauranteRequest);

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

    ActionBuilder criarComentarioRequest = http("criar comentario")
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
                                .to(5)
                                .during(Duration.ofSeconds(5)),
                        constantUsersPerSec(5)
                                .during(Duration.ofSeconds(30)),
                        rampUsersPerSec(5)
                                .to(1)
                                .during(Duration.ofSeconds(5))),
                cenarioCriarCancelarReserva.injectOpen(
                        rampUsersPerSec(1)
                                .to(15)
                                .during(Duration.ofSeconds(5)),
                        constantUsersPerSec(15)
                                .during(Duration.ofSeconds(30)),
                        rampUsersPerSec(15)
                                .to(1)
                                .during(Duration.ofSeconds(5))),
                cenarioCriarComentario.injectOpen(
                        rampUsersPerSec(1)
                                .to(5)
                                .during(Duration.ofSeconds(5)),
                        constantUsersPerSec(5)
                                .during(Duration.ofSeconds(30)),
                        rampUsersPerSec(5)
                                .to(1)
                                .during(Duration.ofSeconds(5))),
                cenarioCadastrarRestaurante.injectOpen(
                        rampUsersPerSec(1)
                                .to(15)
                                .during(Duration.ofSeconds(5)),
                        constantUsersPerSec(15)
                                .during(Duration.ofSeconds(30)),
                        rampUsersPerSec(15)
                                .to(1)
                                .during(Duration.ofSeconds(5))),
                cenarioBuscarRestaurantes.injectOpen(
                        rampUsersPerSec(1)
                                .to(15)
                                .during(Duration.ofSeconds(5)),
                        constantUsersPerSec(15)
                                .during(Duration.ofSeconds(30)),
                        rampUsersPerSec(15)
                                .to(1)
                                .during(Duration.ofSeconds(5))))
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(200),
                        global().failedRequests().count().is(1L));
    }
}