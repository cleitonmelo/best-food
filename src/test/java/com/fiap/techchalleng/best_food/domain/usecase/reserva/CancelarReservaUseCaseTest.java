package com.fiap.techchalleng.best_food.domain.usecase.reserva;

import com.fiap.techchalleng.best_food.domain.gateway.reserva.ReservaInterface;
import com.fiap.techchalleng.best_food.infra.model.ReservaModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class CancelarReservaUseCaseTest {

    @Mock
    private ReservaInterface reservaInterface;

    private CancelarReservaUseCase cancelarReservaUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        cancelarReservaUseCase = new CancelarReservaUseCase(reservaInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCancelarReservaUseCase(){

        UUID reservaId = UUID.randomUUID();
        UUID restauranteId = UUID.randomUUID();
        UUID mesaId = UUID.randomUUID();
        String nome = "teste";
        String telefone = "(11) 99999-9999";
        Integer qtdeLugares = 5;
        LocalDate dataReserva = LocalDate.now();
        LocalTime horaReserva = LocalTime.now();

        when(reservaInterface.buscarReserva(reservaId)).thenReturn(Optional.of(new ReservaModel(reservaId,
                restauranteId,
                mesaId,
                nome,
                telefone,
                qtdeLugares,
                dataReserva,
                horaReserva,
                null)));

        cancelarReservaUseCase.execute(reservaId);

        var output = cancelarReservaUseCase.getOutput();
        assertThat(output.getOutputStatus().getCode()).isEqualTo(200);
        assertThat(output.getOutputStatus().getCodeName()).isEqualTo("OK");
    }

}