package com.fiap.techchalleng.best_food.application.reserva;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/clean.sql",
        "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CancelarReservaControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test

    void devePermitirCancelarReserva() {
        var id = "5f789b39-4295-42c1-a65b-cfca5b987db2";

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("api/v1/reservas/{id}", id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasKey("nome"))
                .body("$", hasKey("telefone"));
    }

}