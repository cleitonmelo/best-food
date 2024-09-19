package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterResponse;
import com.fiap.techchalleng.best_food.application.restaurante.request.CreateRestauranteRequest;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.CreateRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.CreateRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.presenters.restaurante.create.CreateRestaurantePresenter;
import com.fiap.techchalleng.best_food.domain.usecase.restaurante.CreateRestauranteUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.RestauranteAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/restaurantes")
public class CreateRestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private MesaRepository restauranteMesaRepository;

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
                .capacidade(getCapacidade(request.mesas()))
                .cep(request.cep())
                .logradouro(request.logradouro())
                .bairro(request.bairro())
                .cidade(request.cidade())
                .estado(request.estado())
                .mesas(request.mesas())
                .build();

        CreateRestauranteUseCase useCase = new CreateRestauranteUseCase(
                new RestauranteAdapterRepository(restauranteRepository, restauranteMesaRepository)
        );

        useCase.execute(input);

        return useCase.getOutput();
    }

    private Integer getCapacidade(List<Mesa> mesas){
        Integer capacidade = 0;
        for ( Mesa mesa : mesas ){
            capacidade += mesa.lugares();
        }
        return capacidade;
    }
}
