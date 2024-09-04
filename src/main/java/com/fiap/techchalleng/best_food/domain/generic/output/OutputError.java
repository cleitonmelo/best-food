package com.fiap.techchalleng.best_food.domain.generic.output;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OutputError implements OutputInterface {

    private String message;
    private OutputStatus outputStatus;

    @Override
    public Object getBody() {
        return this.message;
    }
}
