package com.fiap.techchalleng.best_food.application.restaurante;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class BuscarRestauranteControllerTest {


    BuscarRestauranteController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        controller = new BuscarRestauranteController();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRetornarPresenter() {
        var presenter = controller.find(null, null, null);
        assertThat(presenter.getBody()).isNotNull();
    }
}
