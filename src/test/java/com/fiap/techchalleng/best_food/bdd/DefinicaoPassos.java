package com.fiap.techchalleng.best_food.bdd;

import com.fiap.techchalleng.best_food.application.reserva.request.CreateReservaRequest;
import com.fiap.techchalleng.best_food.application.restaurante.request.CreateRestauranteRequest;
import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.entity.comentario.Comentario;
import com.fiap.techchalleng.best_food.application.comentario.request.CreateComentarioRequest;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class DefinicaoPassos extends BaseBdd{

    private Response response;

    private Reserva reservaResponse;




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
                .when().post(this.getUriReservas());
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
                .put(this.getUriReservas() + "/{id}", reservaResponse.getId().toString());
    }

    @Então("a reserva é cancelada com sucesso")
    public void reservaCanceladaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("submeter um novo restaurante")
    public Restaurante submeterNovoRestaurante() {
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(Mesa.builder().codigo(1).lugares(4).build());
        mesas.add(Mesa.builder().codigo(2).lugares(3).build());
        mesas.add(Mesa.builder().codigo(3).lugares(2).build());

        var request = CreateRestauranteRequest.builder()
                .nome("Restaurante")
                .tipoCozinha(TipoCozinha.BRASILEIRA)
                .cidade("São Paulo")
                .estado("SP")
                .cep("75000-000")
                .mesas(mesas)
                .build();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post(this.getUriRestaurantes());

        return response.then().extract().as(Restaurante.class);
    }

    @Então("o restaurante é cadastrado com sucesso")
    public void restauranteCadastradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }


    @Quando("submeter um novo comentário")
    public Comentario submeterNovoComentario() {

        var comentarioRequest = new CreateComentarioRequest(null,
                UUID.randomUUID(),
                "Novo comentário",
                LocalDate.now(),
                LocalTime.now());

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(comentarioRequest)
                .when().post(this.getUriComentarios());
        return response.then().extract().as(Comentario.class);
    }

    @Então("o comentário é cadastrado com sucesso")
    public void comentarioRegistradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

   
}