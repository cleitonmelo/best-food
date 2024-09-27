package com.fiap.techchalleng.best_food.domain.usecase.comentario;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.techchalleng.best_food.domain.entity.comentario.Comentario;
import com.fiap.techchalleng.best_food.domain.gateway.comentario.ComentarioInterface;
import com.fiap.techchalleng.best_food.domain.input.comentario.CreateComentarioInput;

public class CreateComentarioUseCaseTest {

	@Mock
    private ComentarioInterface comentarioInterface;

    private CreateComentarioUseCase createComentarioUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        createComentarioUseCase = new CreateComentarioUseCase(comentarioInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarComentarioUseCase(){

        var comentario = Comentario.builder()
                .idReserva(UUID.randomUUID())
                .comentario("Mais um comentario")
                .dataComentario(LocalDate.now())
                .horaComentario(LocalTime.now())
                .build();

        when(comentarioInterface.createComentario(comentario)).thenReturn(comentario);

        var comentarioInput = CreateComentarioInput.builder()
                .idReserva(comentario.getIdReserva())
                .comentario(comentario.getComentario())
                .dataComentario(comentario.getDataComentario())
                .horaComentario(comentario.getHoraComentario())
                .build();

        createComentarioUseCase.execute(comentarioInput);

        var output = createComentarioUseCase.getOutput();
        assertThat(output.getOutputStatus().getCode()).isEqualTo(201);
        assertThat(output.getOutputStatus().getCodeName()).isEqualTo("CREATED");
    }

}