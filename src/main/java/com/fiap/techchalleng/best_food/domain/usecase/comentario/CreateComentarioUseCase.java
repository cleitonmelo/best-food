package com.fiap.techchalleng.best_food.domain.usecase.comentario;

import org.springframework.http.HttpStatus;

import com.fiap.techchalleng.best_food.domain.entity.comentario.Comentario;
import com.fiap.techchalleng.best_food.domain.gateway.comentario.ComentarioInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.comentario.CreateComentarioInput;
import com.fiap.techchalleng.best_food.domain.output.comentario.CreateComentarioOutput;
import com.fiap.techchalleng.best_food.domain.usecase.base.BaseUseCase;
import com.fiap.techchalleng.best_food.infra.repository.ComentarioRepository;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateComentarioUseCase extends BaseUseCase {

    private final ComentarioInterface comentarioRepository;
    private OutputInterface output;

    public void execute(@Valid CreateComentarioInput input) {
    	Comentario comentario = Comentario.builder()
                .idReserva(input.idReserva())
                .comentario(input.comentario())
                .dataComentario(input.dataComentario())
                .horaComentario(input.horaComentario())
                .build();

        Comentario data = comentarioRepository.createComentario(comentario);

        output = new CreateComentarioOutput(
                data,
                this.getStatusCodeOutput("Comentario Criada", HttpStatus.CREATED)
        );

    }

}
