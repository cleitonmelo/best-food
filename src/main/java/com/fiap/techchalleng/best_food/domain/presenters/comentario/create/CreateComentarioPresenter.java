package com.fiap.techchalleng.best_food.domain.presenters.comentario.create;

import java.util.HashMap;
import java.util.Map;

import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.presenter.PresenterInterface;
import com.fiap.techchalleng.best_food.domain.output.comentario.CreateComentarioOutput;

public class CreateComentarioPresenter implements PresenterInterface {

    private final CreateComentarioOutput output;

    public CreateComentarioPresenter(CreateComentarioOutput comentarioOutput) {
        this.output = comentarioOutput;
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = new HashMap<>();
        result.put("id", this.output.getComentario().getId());
        result.put("idReserva", this.output.getComentario().getIdReserva());
        result.put("comentario", this.output.getComentario().getComentario());
        result.put("dataComentario", this.output.getComentario().getDataComentario());
        result.put("horaComentario", this.output.getComentario().getHoraComentario());

        return result;
    }

    @Override
    public OutputInterface getOutput() {
        return this.output;
    }
}
