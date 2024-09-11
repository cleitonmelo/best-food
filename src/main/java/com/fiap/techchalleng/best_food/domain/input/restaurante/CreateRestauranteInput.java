package com.fiap.techchalleng.best_food.domain.input.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateRestauranteInput(
        String nome,
        TipoCozinha tipoCozinha,
        Integer capacidade,
        String logradouro,
        String cidade,
        String estado,
        String cep,
        String bairro,
        List<Mesa> mesas
) {
}
