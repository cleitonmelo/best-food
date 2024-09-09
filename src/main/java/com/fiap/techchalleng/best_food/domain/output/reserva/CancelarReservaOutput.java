package com.fiap.techchalleng.best_food.domain.output.reserva;

import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import lombok.*;

@Builder
@Data
@RequiredArgsConstructor
@Getter
@Setter
public class CancelarReservaOutput implements OutputInterface {

    private Reserva reserva;
    private OutputStatus outputStatus;

    public CancelarReservaOutput(Reserva reserva, OutputStatus outputStatus) {
        this.reserva = reserva;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return reserva;
    }
}
