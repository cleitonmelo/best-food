package com.fiap.techchalleng.best_food.domain.output.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StoreRestauranteOutput implements OutputInterface {

    private Restaurante restaurante;
    private OutputStatus outputStatus;

    @Override
    public Object getBody() {
        return this.restaurante;
    }

}
