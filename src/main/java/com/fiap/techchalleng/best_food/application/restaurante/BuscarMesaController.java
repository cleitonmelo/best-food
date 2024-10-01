package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterListResponse;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.BuscarMesaInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.BuscarMesaOutput;
import com.fiap.techchalleng.best_food.domain.presenters.restaurante.find.BuscarMesaPresenter;
import com.fiap.techchalleng.best_food.domain.usecase.restaurante.BuscarMesaUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.MesaAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/mesa")
public class BuscarMesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping("/{idRestaurante}")
    public ResponseEntity<Object> find(
            @PathVariable UUID idRestaurante,
            @RequestParam(required = false) Boolean reservada) {
        OutputInterface outputInterface = this.getOutputInterface(idRestaurante, reservada);
        if ( outputInterface.getOutputStatus().getCode() != HttpStatus.OK.value() ) {
            return new GenericResponse().response(outputInterface);
        }
        BuscarMesaPresenter presenter = new BuscarMesaPresenter((BuscarMesaOutput) outputInterface);
        return new PresenterListResponse().response(presenter);
    }

    private OutputInterface getOutputInterface(UUID idRestaurante, Boolean reservada){
        BuscarMesaInput input = BuscarMesaInput.builder()
                .idRestaurante(idRestaurante)
                .reservada(reservada)
                .build();

        BuscarMesaUseCase useCase = new BuscarMesaUseCase(
                new MesaAdapterRepository(mesaRepository)
        );

        useCase.execute(input);

        return useCase.getOutput();
    }
}
