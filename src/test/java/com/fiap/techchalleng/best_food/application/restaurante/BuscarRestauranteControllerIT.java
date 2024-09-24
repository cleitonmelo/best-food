package com.fiap.techchalleng.best_food.application.restaurante;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/clean.sql",
        "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BuscarRestauranteControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirCadastrarRestaurante(){

        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("api/v1/restaurantes")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
