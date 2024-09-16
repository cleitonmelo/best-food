package com.fiap.techchalleng.best_food.domain.output.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import lombok.*;

@Builder
@Data
@RequiredArgsConstructor
@Getter
@Setter
public class CreateRestauranteOutput implements OutputInterface {

    private Restaurante restaurante;
    private OutputStatus outputStatus;

    public CreateRestauranteOutput(Restaurante restaurante, OutputStatus outputStatus) {
        this.restaurante = restaurante;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.restaurante;
    }

}
