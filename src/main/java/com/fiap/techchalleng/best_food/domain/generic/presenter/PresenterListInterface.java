package com.fiap.techchalleng.best_food.domain.generic.presenter;

import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;

import java.util.List;
import java.util.Map;

public interface PresenterListInterface {

    List<Map<String, Object>> toArray();

    OutputInterface getOutput();

}
