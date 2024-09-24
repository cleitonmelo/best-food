package com.fiap.techchalleng.best_food.domain.output.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
@Getter
@Setter
public class BuscarRestauranteOutput implements OutputInterface {

    private List<Restaurante> restauranteList;
    private OutputStatus outputStatus;

    public BuscarRestauranteOutput(List<Restaurante> restauranteList, OutputStatus outputStatus) {
        this.restauranteList = restauranteList;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.restauranteList;
    }
}
