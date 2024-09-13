package com.fiap.techchalleng.best_food.application.reserva;

import com.fiap.techchalleng.best_food.application.reserva.request.CreateReservaRequest;
import com.fiap.techchalleng.best_food.domain.gateway.reserva.ReservaInterface;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import com.fiap.techchalleng.best_food.infra.repository.ReservaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CreateReservaControllerTest {

    @Mock
    ReservaRepository reservaRepository;

    @Mock
    MesaRepository mesaRepository;

    CreateReservaController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        controller = new CreateReservaController(reservaRepository, mesaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRetornarPresenter() {
        var request = new CreateReservaRequest(null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Nome",
                "(11) 98765-4321",
                5,
                LocalDate.now(),
                LocalTime.now());

        var presenter = controller.create(request);

        assertThat(presenter.getBody()).isNotNull();

    }
}