package com.fiap.techchalleng.best_food.domain.presenters.restaurante.create;

import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.presenter.PresenterInterface;
import com.fiap.techchalleng.best_food.domain.output.restaurante.CreateRestauranteOutput;

import java.util.HashMap;
import java.util.Map;

public class CreateRestaurantePresenter implements PresenterInterface {

    private final CreateRestauranteOutput output;

    public CreateRestaurantePresenter(CreateRestauranteOutput restauranteOutput) {
        this.output = restauranteOutput;
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = new HashMap<>();
        result.put("id", this.output.getRestaurante().id());
        result.put("nome", this.output.getRestaurante().nome());
        result.put("capacidade", this.output.getRestaurante().capacidadeTotal());
        result.put("tipoCozinha", this.output.getRestaurante().tipoCozinha());
        result.put("mesas", this.output.getRestaurante().mesas());
        return result;
    }

    @Override
    public OutputInterface getOutput() {
        return this.output;
    }
}
