package com.fiap.techchalleng.best_food.domain.presenters.reserva.create;

import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.presenter.PresenterInterface;
import com.fiap.techchalleng.best_food.domain.output.reserva.CreateReservaOutput;

import java.util.HashMap;
import java.util.Map;

public class CreateReservaPresenter implements PresenterInterface {

    private final CreateReservaOutput output;

    public CreateReservaPresenter(CreateReservaOutput reservaOutput) {
        this.output = reservaOutput;
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = new HashMap<>();
        result.put("id", this.output.getReserva().getId());
        result.put("idRestaurante", this.output.getReserva().getIdRestaurante());
        result.put("idMesa", this.output.getReserva().getIdMesa());
        result.put("nome", this.output.getReserva().getNome());
        result.put("telefone", this.output.getReserva().getTelefone());
        result.put("qtdeLugares", this.output.getReserva().getQtdeLugares());
        result.put("dataReserva", this.output.getReserva().getDataReserva());
        result.put("horaReserva", this.output.getReserva().getHoraReserva());
        result.put("dataCancelamentoReserva", this.output.getReserva().getDataCancelamentoReserva());

        return result;
    }

    @Override
    public OutputInterface getOutput() {
        return this.output;
    }
}
