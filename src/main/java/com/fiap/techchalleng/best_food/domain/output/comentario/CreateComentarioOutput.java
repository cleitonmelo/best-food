package com.fiap.techchalleng.best_food.domain.output.comentario;

import com.fiap.techchalleng.best_food.domain.entity.comentario.Comentario;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Data
@RequiredArgsConstructor
@Getter
@Setter
public class CreateComentarioOutput implements OutputInterface {

    private Comentario comentario;
    private OutputStatus outputStatus;

    public CreateComentarioOutput(Comentario comentario, OutputStatus outputStatus) {
        this.comentario = comentario;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.comentario;
    }
}
