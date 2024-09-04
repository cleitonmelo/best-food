package com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.infra.model.MesaModel;
import com.fiap.techchalleng.best_food.infra.model.RestauranteModel;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RestauranteAdapterRepository implements RestauranteInterface {

    private final RestauranteRepository repository;

    private final MesaRepository mesaRepository;

    @Override
    public List<Restaurante> buscaRestaurantes() {
        return List.of();
    }

    @Override
    public Restaurante getRestauranteById(UUID id) {
        return null;
    }

    @Override
    public Restaurante createRestaurante(Restaurante restaurante, List<Mesa> mesas) {
        UUID uuid = UUID.randomUUID();

        RestauranteModel model = RestauranteModel.builder()
                .id(uuid)
                .name(restaurante.nome())
                .tipoCozinha(restaurante.tipoCozinha())
                .build();

        repository.save(model);

        // @todo lista de mesas recebidas no momento do cadastro
        for (Mesa mesa : mesas) {
            MesaModel mesaModel = MesaModel.builder()
                    .id(UUID.randomUUID())
                    .idRestaurante(uuid)
                    .codigo(mesa.codigo())
                    .lugares(mesa.lugares())
                    .reservada(mesa.reservada())
                    .build();

            mesaRepository.save(mesaModel);
        }

        return Restaurante.builder()
                .id(uuid)
                .nome(restaurante.nome())
                .capacidade(100) //@todo incluir getTotalLugares
                .tipoCozinha(restaurante.tipoCozinha())
                .build();
    }
}
