package com.fiap.techchalleng.best_food.domain.usecase.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputError;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.BuscarRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.BuscarRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.CreateRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.usecase.base.BaseUseCase;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BuscarRestauranteUseCase extends BaseUseCase {

    private final RestauranteInterface repository;
    private OutputInterface output;

    public void execute(@Valid BuscarRestauranteInput input) {
        try {

            List<Restaurante> data = List.of();

            if(StringUtils.isEmpty(input.nome()) && StringUtils.isEmpty(input.bairro()) && input.tipoCozinha() == null){
                data = this.repository.getRestaurantes();
            }

            if(StringUtils.isNotEmpty(input.nome())){
                data = this.repository.getRestaurantesByName(input.nome());
            }

            if(StringUtils.isNotEmpty(input.bairro())){
                data = this.repository.getRestaurantesByBairro(input.bairro());
            }

            if(input.tipoCozinha() != null){
                data = this.repository.getRestaurantesByTipoCozinha(input.tipoCozinha());
            }

            if(!data.isEmpty()) {
                this.output = BuscarRestauranteOutput.builder()
                        .restauranteList(data)
                        .outputStatus(this.getStatusCodeOutput(
                                "Restaurante encontrado com sucesso.", HttpStatus.OK))
                        .build();
            } else {
                this.output = BuscarRestauranteOutput.builder()
                        .restauranteList(data)
                        .outputStatus(this.getStatusCodeOutput(
                                "Restaurante não encontrado de acordo com os filtros de busca.", HttpStatus.NOT_FOUND))
                        .build();
            }
        } catch (Exception e) {
            this.output = OutputError.builder()
                    .message(e.getMessage())
                    .outputStatus(this.getStatusCodeOutput(
                            e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY))
                    .build();
        }

    }
}
