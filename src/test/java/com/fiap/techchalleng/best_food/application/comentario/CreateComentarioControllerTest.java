package com.fiap.techchalleng.best_food.application.comentario;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.techchalleng.best_food.application.comentario.request.CreateComentarioRequest;
import com.fiap.techchalleng.best_food.infra.repository.ComentarioRepository;

public class CreateComentarioControllerTest {


    @Mock
    ComentarioRepository comentarioRepository;

    CreateComentarioController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        controller = new CreateComentarioController(comentarioRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRetornarPresenter() {
        var request = new CreateComentarioRequest(null,
                UUID.randomUUID(),
                "Outro comentário ",
                LocalDate.now(),
                LocalTime.now());

        var presenter = controller.create(request);

        assertThat(presenter.getBody()).isNotNull();

    }
}