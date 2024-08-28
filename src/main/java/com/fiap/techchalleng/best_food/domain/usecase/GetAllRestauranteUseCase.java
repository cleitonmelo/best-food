package com.fiap.techchalleng.best_food.domain.usecase;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import com.fiap.techchalleng.best_food.domain.output.restaurante.GetAllRestauranteOutput;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GetAllRestauranteUseCase {

    private final RestauranteInterface restauranteInterface;
    private OutputInterface outputStatus;

    public void execute() throws Exception{
        try{
            List<Restaurante> listRestaurantes = restauranteInterface.buscaRestaurantes();

            OutputStatus status = OutputStatus.builder()
                    .code(200)
                    .codeName("OK")
                    .message("Lista de Restaurantes")
                    .build();

            this.outputStatus = GetAllRestauranteOutput
                    .builder()
                    .restaurantes(listRestaurantes)
                    .outputStatus(status)
                    .build();

        }catch (Exception e){
            OutputStatus status = OutputStatus.builder()
                    .code(500)
                    .codeName("Internal Server Error")
                    .message(e.getMessage())
                    .build();

            this.outputStatus = GetAllRestauranteOutput
                    .builder()
                    .outputStatus(status)
                    .build();
        }
    }
}
