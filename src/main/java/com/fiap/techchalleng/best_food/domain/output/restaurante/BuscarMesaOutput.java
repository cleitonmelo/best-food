package com.fiap.techchalleng.best_food.domain.output.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import lombok.*;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
@Getter
@Setter
public class BuscarMesaOutput implements OutputInterface {

    private List<Mesa> mesaList;
    private OutputStatus outputStatus;

    public BuscarMesaOutput(List<Mesa> mesaList, OutputStatus outputStatus) {
        this.mesaList = mesaList;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.mesaList;
    }

}
