package com.fiap.techchalleng.best_food.domain.usecase.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputError;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import com.fiap.techchalleng.best_food.domain.input.restaurante.CreateRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.CreateRestauranteOutput;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
@RequiredArgsConstructor
public class CreateRestauranteUseCase {

    private final RestauranteInterface repository;
    private OutputInterface output;

    public void execute(@Valid CreateRestauranteInput input) {
        try {
            Restaurante restaurante = Restaurante.builder()
                    .nome(input.nome())
                    .tipoCozinha(input.tipoCozinha())
                    .capacidade(input.capacidade())
                    .build();

            // @todo receber do input do create
            ArrayList<Mesa> mesas = new ArrayList<>();
            mesas.add(Mesa.builder().codigo(1).lugares(6).reservada(false).build());
            mesas.add(Mesa.builder().codigo(2).lugares(6).reservada(false).build());
            mesas.add(Mesa.builder().codigo(3).lugares(6).reservada(false).build());

            Restaurante data = this.repository.createRestaurante(restaurante, mesas);

            this.output = CreateRestauranteOutput.builder()
                    .restaurante(data)
                    .outputStatus(this.getStatusCodeCreated())
                    .build();
        } catch (Exception e) {
            this.output = OutputError.builder()
                    .message(e.getMessage())
                    .outputStatus(this.getStatusCodeBadRequest(e.getMessage()))
                    .build();
        }

    }

    //@todo refactory criar classe com responsabilidade de montar esse retorno
    private OutputStatus getStatusCodeCreated()
    {
        return OutputStatus.builder()
                .code(HttpStatus.CREATED.value())
                .codeName(HttpStatus.CREATED.name())
                .message("Restaurante Criado com sucesso")
                .build();
    }

    private OutputStatus getStatusCodeBadRequest(String message)
    {
        return OutputStatus.builder()
                .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .codeName(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .message(message)
                .build();
    }

}
