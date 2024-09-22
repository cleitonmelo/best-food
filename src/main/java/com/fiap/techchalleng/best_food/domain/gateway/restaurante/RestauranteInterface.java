package com.fiap.techchalleng.best_food.domain.gateway.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;

import java.util.List;
import java.util.UUID;

public interface RestauranteInterface {

    List<Restaurante> getRestaurantes();

    Restaurante getRestauranteById(UUID id);

    List<Restaurante> getRestaurantesByName(String nome);

    List<Restaurante> getRestaurantesByBairro(String bairro);

    List<Restaurante> getRestaurantesByTipoCozinha(TipoCozinha tipoCozinha);

    Restaurante createRestaurante(Restaurante restaurante, List<Mesa> mesas);
}
