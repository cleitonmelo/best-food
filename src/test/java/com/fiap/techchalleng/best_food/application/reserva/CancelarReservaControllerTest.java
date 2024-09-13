package com.fiap.techchalleng.best_food.application.reserva;

import com.fiap.techchalleng.best_food.infra.model.ReservaModel;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import com.fiap.techchalleng.best_food.infra.repository.ReservaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CancelarReservaControllerTest {

    @Mock
    ReservaRepository reservaRepository;

    @Mock
    MesaRepository mesaRepository;

    CancelarReservaController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        controller = new CancelarReservaController(reservaRepository, mesaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRetornarPresenter() {
        UUID reservaId = UUID.randomUUID();
        UUID restauranteId = UUID.randomUUID();
        UUID mesaId = UUID.randomUUID();
        String nome = "teste";
        String telefone = "(11) 99999-9999";
        Integer qtdeLugares = 5;
        LocalDate dataReserva = LocalDate.now();
        LocalTime horaReserva = LocalTime.now();

        when(reservaRepository.findById(reservaId)).thenReturn(
                Optional.of(new ReservaModel(reservaId,
                        restauranteId,
                        mesaId,
                        nome,
                        telefone,
                        qtdeLugares,
                        dataReserva,
                        horaReserva,
                        null)));

        var presenter = controller.cancelarReserva(reservaId);

        assertThat(presenter.getBody()).isNotNull();

    }
}