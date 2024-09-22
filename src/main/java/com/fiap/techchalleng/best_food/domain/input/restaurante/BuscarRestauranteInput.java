package com.fiap.techchalleng.best_food.domain.input.restaurante;

import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import lombok.Builder;

@Builder
public record BuscarRestauranteInput (
        String nome,
        String bairro,
        TipoCozinha tipoCozinha
) {
}
