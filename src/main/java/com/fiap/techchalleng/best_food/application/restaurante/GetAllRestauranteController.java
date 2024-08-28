package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterResponse;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.output.restaurante.GetAllRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.presenters.restaurante.list.ListRestaurantePresenter;
import com.fiap.techchalleng.best_food.domain.usecase.GetAllRestauranteUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.RestauranteEntityRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restaurantes")
public class GetAllRestauranteController {

    private final RestauranteRepository repository;

    @GetMapping
    public ResponseEntity<Object> findAll() throws Exception{

        GetAllRestauranteUseCase useCase = new GetAllRestauranteUseCase(
                new RestauranteEntityRepository(this.repository)
        );

        useCase.execute();

        OutputInterface outputInterface = useCase.getOutputStatus();
        if (outputInterface.getOutputStatus().getCode() != HttpStatus.OK.value() ) {
           return new GenericResponse().response(outputInterface);
        }

        ListRestaurantePresenter presenter = new ListRestaurantePresenter((GetAllRestauranteOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}
