package com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.infra.model.RestauranteModel;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RestauranteAdapterRepository implements RestauranteInterface {

    private final RestauranteRepository repository;

    @Override
    public List<Restaurante> buscaRestaurantes() {
        return List.of();
    }

    @Override
    public Restaurante getRestauranteById(UUID id) {
        return null;
    }

    @Override
    public Restaurante createRestaurante(Restaurante restaurante) {
        UUID uuid = UUID.randomUUID();

        RestauranteModel model = RestauranteModel.builder()
                .id(uuid)
                .name(restaurante.nome())
                .capacidade(restaurante.capacidade())
                .tipoCozinha(restaurante.tipoCozinha())
                .build();

        repository.save(model);

        return Restaurante.builder()
                .id(uuid)
                .nome(restaurante.nome())
                .capacidade(restaurante.capacidade())
                .tipoCozinha(restaurante.tipoCozinha())
                .build();
    }
}
