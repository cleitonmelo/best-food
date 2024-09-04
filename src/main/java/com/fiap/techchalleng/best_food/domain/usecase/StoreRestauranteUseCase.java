package com.fiap.techchalleng.best_food.domain.usecase;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.StoreRestauranteInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputError;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import com.fiap.techchalleng.best_food.domain.input.restaurante.StoreRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.StoreRestauranteOutput;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class StoreRestauranteUseCase {

    private final StoreRestauranteInterface repository;
    private OutputInterface output;

    public void execute(StoreRestauranteInput input) {
        try {
            Restaurante restaurante = Restaurante.builder()
                    .nome(input.nome())
                    .build();

            Restaurante data = this.repository.storeRestaurante(restaurante);

            this.output = StoreRestauranteOutput.builder()
                    .restaurante(data)
                    .outputStatus(this.getStatusCodeCreated())
                    .build();

        }catch (Exception e) {
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
                .code(HttpStatus.BAD_REQUEST.value())
                .codeName(HttpStatus.BAD_REQUEST.name())
                .message(message)
                .build();
    }

}
