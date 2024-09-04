package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterResponse;
import com.fiap.techchalleng.best_food.application.restaurante.request.CreateRestauranteRequest;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.CreateRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.CreateRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.presenters.restaurante.create.CreateRestaurantePresenter;
import com.fiap.techchalleng.best_food.domain.usecase.restaurante.CreateRestauranteUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.RestauranteAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/restaurantes")
public class CreateRestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateRestauranteRequest request) {
        OutputInterface outputInterface = this.getOutputInterface(request);

        if ( outputInterface.getOutputStatus().getCode() != HttpStatus.CREATED.value() ) {
            return new GenericResponse().response(outputInterface);
        }

        CreateRestaurantePresenter presenter = new CreateRestaurantePresenter((CreateRestauranteOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }

    private OutputInterface getOutputInterface(CreateRestauranteRequest request){
        CreateRestauranteInput input = CreateRestauranteInput.builder()
                .nome(request.nome())
                .tipoCozinha(request.tipoCozinha())
                .capacidade(request.capacidade())
                .build();

        CreateRestauranteUseCase useCase = new CreateRestauranteUseCase(
                new RestauranteAdapterRepository(restauranteRepository)
        );

        useCase.execute(input);

        return useCase.getOutput();
    }
}