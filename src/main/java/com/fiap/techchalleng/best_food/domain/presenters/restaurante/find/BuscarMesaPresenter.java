package com.fiap.techchalleng.best_food.domain.presenters.restaurante.find;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.presenter.PresenterListInterface;
import com.fiap.techchalleng.best_food.domain.output.restaurante.BuscarMesaOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuscarMesaPresenter implements PresenterListInterface {
    private final BuscarMesaOutput output;

    public BuscarMesaPresenter(BuscarMesaOutput output) {
        this.output = output;
    }

    @Override
    public List<Map<String, Object>> toArray() {
        List<Map<String, Object>> resultList = new ArrayList<>();

        for(Mesa mesa : this.output.getMesaList()){
            Map<String, Object> mesaResult = new HashMap<>();
            mesaResult.put("codigo", mesa.codigo());
            mesaResult.put("lugares", mesa.lugares());
            mesaResult.put("reservada", mesa.reservada());
            resultList.add(mesaResult);
        }
        return resultList;
    }

    @Override
    public OutputInterface getOutput() {
        return this.output;
    }
}
