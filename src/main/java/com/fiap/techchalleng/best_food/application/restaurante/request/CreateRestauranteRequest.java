package com.fiap.techchalleng.best_food.application.restaurante.request;

import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateRestauranteRequest(
        UUID id,
        String nome,
        TipoCozinha tipoCozinha,
        Integer capacidade
) {
}
