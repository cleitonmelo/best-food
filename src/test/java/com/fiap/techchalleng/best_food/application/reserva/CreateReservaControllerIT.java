package com.fiap.techchalleng.best_food.application.reserva;

import com.fiap.techchalleng.best_food.application.reserva.request.CreateReservaRequest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateReservaControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirCriarReserva() {
        var request = new CreateReservaRequest(null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Nome",
                "(11) 98765-4321",
                5,
                LocalDate.now(),
                LocalTime.now());

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("api/v1/reservas")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasKey("id"))
                .body("$", hasKey("idRestaurante"))
                .body("$", hasKey("idMesa"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("telefone"))
                .body("nome", equalTo(request.nome()))
                .body("telefone", equalTo(request.telefone()));
    }

}