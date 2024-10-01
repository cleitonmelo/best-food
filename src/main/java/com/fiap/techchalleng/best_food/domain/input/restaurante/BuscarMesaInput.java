package com.fiap.techchalleng.best_food.domain.input.restaurante;

import lombok.Builder;

import java.util.UUID;

@Builder
public record BuscarMesaInput(
        UUID idRestaurante,
        Boolean reservada) {
}
