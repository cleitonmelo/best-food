package com.fiap.techchalleng.best_food.domain.gateway.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;

import java.util.List;
import java.util.UUID;

public interface RestauranteInterface {

    List<Restaurante> buscaRestaurantes();

    Restaurante getRestauranteById(UUID id);

    Restaurante createRestaurante(Restaurante restaurante, List<Mesa> mesas);
}
