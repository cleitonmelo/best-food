package com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.StoreRestauranteInterface;
import com.fiap.techchalleng.best_food.infra.model.RestauranteModel;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class StoreRestauranteRepository implements StoreRestauranteInterface {

    private final RestauranteRepository repository;

    @Override
    public Restaurante storeRestaurante(Restaurante restaurante) {
        UUID uuid = UUID.randomUUID();

        RestauranteModel model = RestauranteModel.builder()
                .id(uuid)
                .name(restaurante.nome())
                .build();

        repository.save(model);

        return Restaurante.builder()
                .id(uuid)
                .nome(restaurante.nome())
                .build();
    }
}
