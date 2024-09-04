package com.fiap.techchalleng.best_food.domain.generic.output;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class OutputError implements OutputInterface {

    public final String message;
    public final OutputStatus outputStatus;

    public OutputError(String message, OutputStatus outputStatus) {
        this.message = message;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return null;
    }
}
