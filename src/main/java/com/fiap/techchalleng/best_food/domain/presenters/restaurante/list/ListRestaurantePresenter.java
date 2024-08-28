package com.fiap.techchalleng.best_food.domain.presenters.restaurante.list;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.presenter.PresenterInterface;
import com.fiap.techchalleng.best_food.domain.output.restaurante.GetAllRestauranteOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListRestaurantePresenter implements PresenterInterface {

    private final GetAllRestauranteOutput getAllRestauranteOutput;

    public ListRestaurantePresenter(GetAllRestauranteOutput getAllRestauranteOutput) {
        this.getAllRestauranteOutput = getAllRestauranteOutput;
    }

    @Override
    public Map<String, Object> toArray() {
        List<Restaurante> restaurantes = this.getAllRestauranteOutput.getRestaurantes();
        Map<String, Object> result = new HashMap<>();
        List<Map<String,Object>> restaurantesMapList = new ArrayList<>();

        for (Restaurante restaurante : restaurantes) {
            Map<String, Object> restauranteMap = new HashMap<>();
            restauranteMap.put("id", restaurante.id());
            restauranteMap.put("nome", restaurante.nome());
            restaurantesMapList.add(restauranteMap);
        }
        result.put("restaurantes", restaurantesMapList);
        return result;
    }

    @Override
    public OutputInterface getOutput() {
        return this.getAllRestauranteOutput;
    }
}
