package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterListResponse;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.BuscarMesaInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.BuscarRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.presenters.restaurante.find.BuscarRestaurantePresenter;
import com.fiap.techchalleng.best_food.domain.usecase.restaurante.BuscarMesaUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.MesaAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mesa")
public class BuscarMesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    public ResponseEntity<Object> find(
            @RequestParam(required = false) Boolean reservada) {
        OutputInterface outputInterface = this.getOutputInterface(reservada);
        if ( outputInterface.getOutputStatus().getCode() != HttpStatus.OK.value() ) {
            return new GenericResponse().response(outputInterface);
        }
        BuscarRestaurantePresenter presenter = new BuscarRestaurantePresenter((BuscarRestauranteOutput) outputInterface);
        return new PresenterListResponse().response(presenter);
    }

    private OutputInterface getOutputInterface(Boolean reservada){
        BuscarMesaInput input = BuscarMesaInput.builder()
                .reservada(reservada)
                .build();

        BuscarMesaUseCase useCase = new BuscarMesaUseCase(
                new MesaAdapterRepository(mesaRepository)
        );

        useCase.execute(input);

        return useCase.getOutput();
    }
}
