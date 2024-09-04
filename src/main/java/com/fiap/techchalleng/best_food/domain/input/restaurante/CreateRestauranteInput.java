package com.fiap.techchalleng.best_food.domain.input.restaurante;

import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CreateRestauranteInput(
        String nome,
        TipoCozinha tipoCozinha,
        @NotEmpty(message = "capacidade obrigatória")
        Integer capacidade,
        String logradouro,
        String cidade,
        String estado,
        String cep,
        String bairro
) {
}
