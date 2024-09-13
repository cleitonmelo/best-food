package com.fiap.techchalleng.best_food.domain.usecase.reserva;

import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.domain.gateway.reserva.ReservaInterface;
import com.fiap.techchalleng.best_food.domain.input.reserva.CreateReservaInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class CreateReservaUseCaseTest {

    @Mock
    private ReservaInterface reservaInterface;

    private CreateReservaUseCase createReservaUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        createReservaUseCase = new CreateReservaUseCase(reservaInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarReservaUseCase(){

        var reserva = Reserva.builder()
                .idRestaurante(UUID.randomUUID())
                .idMesa(UUID.randomUUID())
                .nome("Teste")
                .telefone("(11) 99999-9999")
                .qtdeLugares(5)
                .dataReserva(LocalDate.now())
                .horaReserva(LocalTime.now())
                .build();

        when(reservaInterface.createReserva(reserva)).thenReturn(reserva);

        var reservaInput = CreateReservaInput.builder()
                .idRestaurante(reserva.getIdRestaurante())
                .idMesa(reserva.getIdMesa())
                .nome(reserva.getNome())
                .telefone(reserva.getTelefone())
                .qtdeLugares(reserva.getQtdeLugares())
                .dataReserva(reserva.getDataReserva())
                .horaReserva(reserva.getHoraReserva())
                .build();

        createReservaUseCase.execute(reservaInput);

        var output = createReservaUseCase.getOutput();
        assertThat(output.getOutputStatus().getCode()).isEqualTo(201);
        assertThat(output.getOutputStatus().getCodeName()).isEqualTo("CREATED");
    }

}