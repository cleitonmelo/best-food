package com.fiap.techchalleng.best_food.bdd;

import com.fiap.techchalleng.best_food.application.reserva.request.CreateReservaRequest;
import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class DefinicaoPassos {

    private Response response;

    private Reserva reservaResponse;

    private String ENDPOINT_RESERVAS = "http://localhost:8080/api/v1/reservas";

    @Quando("submeter uma nova reserva")
    public Reserva submeterNovaReserva() {

        var reservaRequest = new CreateReservaRequest(null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Nome",
                "(11) 98765-4321",
                5,
                LocalDate.now(),
                LocalTime.now());

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reservaRequest)
                .when().post(ENDPOINT_RESERVAS);
        return response.then().extract().as(Reserva.class);
    }

    @Então("a reserva é cadastrada com sucesso")
    public void reservaRegistradaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Dado("que uma reserva já foi cadastrada")
    public void reservaJaCadastrada() {
        reservaResponse = submeterNovaReserva();
    }

    @Quando("requisitar o cancelamento da reserva")
    public void cancelarReserva() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(ENDPOINT_RESERVAS + "/{id}", reservaResponse.getId().toString());
    }

    @Então("a reserva é cancelada com sucesso")
    public void reservaCanceladaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }


}
