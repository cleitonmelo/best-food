package com.fiap.techchalleng.best_food.domain.input.restaurante;

import lombok.Builder;

@Builder
public record BuscarMesaInput(
        Boolean reservada) {
}
