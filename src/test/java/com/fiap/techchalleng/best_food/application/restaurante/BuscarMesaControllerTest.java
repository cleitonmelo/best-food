package com.fiap.techchalleng.best_food.application.restaurante;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BuscarMesaControllerTest {


    BuscarMesaController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        controller = new BuscarMesaController();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRetornarPresenter() {
        var presenter = controller.find(UUID.randomUUID(), null);
        assertThat(presenter.getBody()).isNotNull();
    }
}
