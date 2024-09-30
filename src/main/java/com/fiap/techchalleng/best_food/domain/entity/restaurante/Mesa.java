package com.fiap.techchalleng.best_food.domain.entity.restaurante;

import lombok.Builder;
import java.util.UUID;


@Builder
public record Mesa (
        UUID id,
        Integer codigo,
        Integer lugares,
        Boolean reservada
){
}
