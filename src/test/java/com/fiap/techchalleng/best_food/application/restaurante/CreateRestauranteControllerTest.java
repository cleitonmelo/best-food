package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.application.restaurante.request.CreateRestauranteRequest;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateRestauranteControllerTest {


    CreateRestauranteController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        controller = new CreateRestauranteController();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRetornarPresenter() {
        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas.add(Mesa.builder().codigo(1).lugares(4).build());
        mesas.add(Mesa.builder().codigo(2).lugares(3).build());
        mesas.add(Mesa.builder().codigo(3).lugares(2).build());

        var request = CreateRestauranteRequest.builder()
                .bairro("Cidade Jardim")
                .cidade("São Paulo")
                .estado("SP")
                .cep("078555-000")
                .nome("Restaurante de Teste")
                .tipoCozinha(TipoCozinha.BRASILEIRA)
                .mesas(mesas)
                .build();

        var presenter = controller.create(request);

        assertThat(presenter.getBody()).isNotNull();

    }
}
