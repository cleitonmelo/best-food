package com.fiap.techchalleng.best_food.domain.presenters.restaurante.create;

import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.presenter.PresenterInterface;
import com.fiap.techchalleng.best_food.domain.output.restaurante.CreateRestauranteOutput;

import java.util.HashMap;
import java.util.Map;

public class CreateRestaurantePresenter implements PresenterInterface {

    private final CreateRestauranteOutput createRestauranteOutput;

    public CreateRestaurantePresenter(CreateRestauranteOutput createRestauranteOutput) {
        this.createRestauranteOutput = createRestauranteOutput;
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = new HashMap<>();
        result.put("id", this.createRestauranteOutput.getRestaurante().id());
        result.put("nome", this.createRestauranteOutput.getRestaurante().nome());
        return result;
    }

    @Override
    public OutputInterface getOutput() {
        return null;
    }
}
