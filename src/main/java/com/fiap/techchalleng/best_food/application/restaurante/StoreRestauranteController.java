package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterResponse;
import com.fiap.techchalleng.best_food.application.restaurante.request.StoreRestauranteRequest;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.StoreRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.StoreRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.presenters.restaurante.create.StoreRestaurantePresenter;
import com.fiap.techchalleng.best_food.domain.usecase.StoreRestauranteUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.StoreRestauranteRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/restaurantes")
public class StoreRestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody StoreRestauranteRequest request) {
        OutputInterface outputInterface = this.getOutputInterface(request);

        if ( outputInterface.getOutputStatus().getCode() != HttpStatus.CREATED.value() ) {
            return new GenericResponse().response(outputInterface);
        }

        StoreRestaurantePresenter presenter = new StoreRestaurantePresenter((StoreRestauranteOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }

    private OutputInterface getOutputInterface(StoreRestauranteRequest request){
        StoreRestauranteInput input = StoreRestauranteInput.builder()
                .nome(request.nome())
                .build();

        StoreRestauranteUseCase useCase = new StoreRestauranteUseCase(
                new StoreRestauranteRepository(restauranteRepository)
        );

        useCase.execute(input);

        return useCase.getOutput();
    }
}
