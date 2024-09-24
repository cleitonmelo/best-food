package com.fiap.techchalleng.best_food.domain.presenters.restaurante.find;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.presenter.PresenterListInterface;
import com.fiap.techchalleng.best_food.domain.output.restaurante.BuscarRestauranteOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuscarRestaurantePresenter implements PresenterListInterface {

    private final BuscarRestauranteOutput output;

    public BuscarRestaurantePresenter(BuscarRestauranteOutput output){
        this.output = output;
    }

    @Override
    public List<Map<String, Object>> toArray() {

        List<Map<String, Object>> resultList = new ArrayList<>();

        for(Restaurante restaurante : this.output.getRestauranteList()) {
            Map<String, Object> restauranteResult = new HashMap<>();
            restauranteResult.put("id", restaurante.id());
            restauranteResult.put("nome", restaurante.nome());
            restauranteResult.put("capacidade", restaurante.capacidadeTotal());
            restauranteResult.put("tipoCozinha", restaurante.tipoCozinha());
            restauranteResult.put("logradouro", restaurante.logradouro());
            restauranteResult.put("bairro", restaurante.bairro());
            restauranteResult.put("cep", restaurante.cep());
            restauranteResult.put("cidade", restaurante.cidade());
            restauranteResult.put("estado", restaurante.estado());
            restauranteResult.put("mesas", restaurante.mesas());
            resultList.add(restauranteResult);
        }
        return resultList;
    }

    @Override
    public OutputInterface getOutput() {
        return this.output;
    }
}
