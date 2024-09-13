package com.fiap.techchalleng.best_food.domain.usecase.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputError;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.CreateRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.CreateRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.usecase.base.BaseUseCase;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
@RequiredArgsConstructor
public class CreateRestauranteUseCase extends BaseUseCase {

    private final RestauranteInterface repository;
    private OutputInterface output;

    public void execute(@Valid CreateRestauranteInput input) {
        try {
            Restaurante restaurante = Restaurante.builder()
                    .nome(input.nome())
                    .tipoCozinha(input.tipoCozinha())
                    .cep(input.cep())
                    .logradouro(input.logradouro())
                    .cidade(input.cidade())
                    .bairro(input.bairro())
                    .estado(input.estado())
                    .capacidade(input.capacidade())
                    .build();

            Restaurante data = this.repository.createRestaurante(restaurante, this.getMesas(input));

            this.output = CreateRestauranteOutput.builder()
                    .restaurante(data)
                    .outputStatus(this.getStatusCodeOutput(
                            "Restaurante Cadastrado com Sucesso", HttpStatus.CREATED))
                    .build();

        } catch (Exception e) {
            this.output = OutputError.builder()
                    .message(e.getMessage())
                    .outputStatus(this.getStatusCodeOutput(
                            e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY))
                    .build();
        }

    }

    private ArrayList<Mesa> getMesas(CreateRestauranteInput input){
        ArrayList<Mesa> mesas = new ArrayList<>();
        for (Mesa mesa : input.mesas()) {
            mesas.add(Mesa.builder()
                    .codigo(mesa.codigo())
                    .lugares(mesa.lugares())
                    .reservada(false).build());
        }
        return mesas;
    }
}
