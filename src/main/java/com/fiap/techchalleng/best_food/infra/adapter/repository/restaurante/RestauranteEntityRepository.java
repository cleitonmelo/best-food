package com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RestauranteEntityRepository implements RestauranteInterface {

    private final RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> buscaRestaurantes() {
        return List.of();
    }

    @Override
    public Restaurante getRestauranteById(UUID id) {
        return null;
    }
}
