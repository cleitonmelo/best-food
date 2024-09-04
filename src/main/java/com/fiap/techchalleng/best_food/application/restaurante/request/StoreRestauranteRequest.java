package com.fiap.techchalleng.best_food.application.restaurante.request;

import java.util.UUID;

public record StoreRestauranteRequest(
        UUID id,
        String nome
) {
}
