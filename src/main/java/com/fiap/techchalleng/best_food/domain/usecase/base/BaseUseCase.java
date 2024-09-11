package com.fiap.techchalleng.best_food.domain.usecase.base;

import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import org.springframework.http.HttpStatus;

public abstract class BaseUseCase {

    public OutputStatus getStatusCodeOutput(String message, HttpStatus statusCode)
    {
        return OutputStatus.builder()
                .code(statusCode.value())
                .codeName(statusCode.name())
                .message(message)
                .build();
    }

}
