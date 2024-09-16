package com.fiap.techchalleng.best_food.application.restaurante.request;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CreateRestauranteRequest(
        UUID id,
        String nome,
        TipoCozinha tipoCozinha,
        Integer capacidade,
        List<Mesa> mesas
) {
}
