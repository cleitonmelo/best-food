package com.fiap.techchalleng.best_food.domain.entity.restaurante;

import lombok.Builder;

@Builder
public record Mesa (
        Integer codigo,
        Integer lugares,
        Boolean reservada
){
}
