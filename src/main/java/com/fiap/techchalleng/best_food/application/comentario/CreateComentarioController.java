package com.fiap.techchalleng.best_food.application.comentario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.techchalleng.best_food.application.comentario.request.CreateComentarioRequest;
import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterResponse;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.comentario.CreateComentarioInput;
import com.fiap.techchalleng.best_food.domain.output.comentario.CreateComentarioOutput;
import com.fiap.techchalleng.best_food.domain.presenters.comentario.create.CreateComentarioPresenter;
import com.fiap.techchalleng.best_food.domain.usecase.comentario.CreateComentarioUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.comentario.ComentarioAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.ComentarioRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/comentarios")
public class CreateComentarioController {


    private final ComentarioRepository comentarioRepository;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateComentarioRequest request) {
        OutputInterface outputInterface = this.getOutputInterface(request);

        if (outputInterface.getOutputStatus().getCode() != HttpStatus.CREATED.value()) {
            return new GenericResponse().response(outputInterface);
        }

        CreateComentarioPresenter presenter = new CreateComentarioPresenter((CreateComentarioOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }

    private OutputInterface getOutputInterface(CreateComentarioRequest request) {
        CreateComentarioInput input = CreateComentarioInput.builder()
                .idReserva(request.idReserva())
                .comentario(request.comentario())
                .dataComentario(request.dataComentario())
                .horaComentario(request.horaComentario())
                .build();

        CreateComentarioUseCase useCase = new CreateComentarioUseCase(
                new ComentarioAdapterRepository(comentarioRepository)
        );

        useCase.execute(input);

        return useCase.getOutput();

    }
}
