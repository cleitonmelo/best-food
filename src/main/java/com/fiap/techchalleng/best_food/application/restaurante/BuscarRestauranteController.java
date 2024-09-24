package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterListResponse;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.BuscarRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.BuscarRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.presenters.restaurante.find.BuscarRestaurantePresenter;
import com.fiap.techchalleng.best_food.domain.usecase.restaurante.BuscarRestauranteUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.RestauranteAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/restaurantes")
public class BuscarRestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private MesaRepository restauranteMesaRepository;

    @GetMapping
    public ResponseEntity<Object> find(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String bairro,
            @RequestParam(required = false) TipoCozinha tipoCozinha){
        OutputInterface outputInterface = this.getOutputInterface(nome, bairro, tipoCozinha);

        if ( outputInterface.getOutputStatus().getCode() != HttpStatus.OK.value() ) {
            return new GenericResponse().response(outputInterface);
        }

        BuscarRestaurantePresenter presenter = new BuscarRestaurantePresenter((BuscarRestauranteOutput) outputInterface);
        return new PresenterListResponse().response(presenter);
    }

    private OutputInterface getOutputInterface(String nome, String bairro, TipoCozinha tipoCozinha){
        BuscarRestauranteInput input = BuscarRestauranteInput.builder()
                .nome(nome)
                .bairro(bairro)
                .tipoCozinha(tipoCozinha)
                .build();

        BuscarRestauranteUseCase useCase = new BuscarRestauranteUseCase(
                new RestauranteAdapterRepository(restauranteRepository, restauranteMesaRepository)
        );

        useCase.execute(input);

        return useCase.getOutput();
    }
}
