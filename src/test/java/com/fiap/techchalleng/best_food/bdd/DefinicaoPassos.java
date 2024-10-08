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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class DefinicaoPassos extends BaseBdd{

    private Response response;

    private Reserva reservaResponse;

    private List<Restaurante> restauranteList;

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

        var request = new CreateRestauranteRequest(
                null,
                "Restaurante de teste",
                TipoCozinha.BRASILEIRA,
                "Cidade Jardim",
                "Rua Cidade Jardim, 168",
                "São Paulo",
                "SP",
                "08799-000",
                mesas);

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(this.getUriRestaurantes());

        return response.then().extract().as(Restaurante.class);
    }

    @Então("o restaurante é cadastrado com sucesso")
    public void restauranteCadastradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Dado("que existem restaurantes cadastrados")
    public void existeRestaurante(){
        restauranteList = List.of(submeterNovoRestaurante());
    }

    @Quando("buscar restaurantes sem filtro")
    public List<Restaurante> buscaRestaurantesSemFiltro(){

        Response response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(this.getUriRestaurantes());

        Restaurante[] restaurantesArray = response.then().extract().as(Restaurante[].class);

        return Arrays.asList(restaurantesArray);
    }

    @Então("deve retornar todos os restaurantes cadastrados")
    public void restauranteRetornadoComSucesso(){
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("buscar mesas de um restaurante")
    public List<Mesa> buscaMesa(){

        Response response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(this.getURIMesa()+"/65b1bbee-c784-4457-be6d-d00b0be5c9e0");

        Mesa[] mesasArray = response.then().extract().as(Mesa[].class);

        return Arrays.asList(mesasArray);
    }

    @Então("deve retornar todas as mesas do restaurante")
    public void MesaRetornadaComSucesso(){
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
