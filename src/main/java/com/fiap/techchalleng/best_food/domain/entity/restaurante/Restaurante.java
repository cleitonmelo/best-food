package com.fiap.techchalleng.best_food.domain.entity.restaurante;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Restaurante(UUID id, String nome) {

}
