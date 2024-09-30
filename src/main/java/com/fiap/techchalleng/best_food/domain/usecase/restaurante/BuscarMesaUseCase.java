package com.fiap.techchalleng.best_food.domain.usecase.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.MesaInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputError;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.BuscarMesaInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.BuscarMesaOutput;
import com.fiap.techchalleng.best_food.domain.usecase.base.BaseUseCase;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BuscarMesaUseCase extends BaseUseCase {

    private final MesaInterface repository;
    private OutputInterface output;

    public void execute(@Valid BuscarMesaInput input) {
        try {

            List<Mesa> data = List.of();

            if(input.reservada() == null){
                data = this.repository.getMesas();
            }

            if(!data.isEmpty()) {
                this.output = BuscarMesaOutput.builder()
                        .mesaList(data)
                        .outputStatus(this.getStatusCodeOutput(
                                "Restaurante encontrado com sucesso.", HttpStatus.OK))
                        .build();
            } else {
                this.output = BuscarMesaOutput.builder()
                        .mesaList(data)
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
