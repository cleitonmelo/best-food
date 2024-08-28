package com.fiap.techchalleng.best_food.domain.generic.output;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OutputStatus {

    private final int code;
    private final String codeName;
    private final String message;

}
