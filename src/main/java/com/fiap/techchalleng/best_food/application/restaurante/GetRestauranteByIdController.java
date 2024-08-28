package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.usecase.GetRestauranteByIdUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.RestauranteEntityRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurante")
public class GetRestauranteByIdController {

    private final RestauranteRepository restauranteRepository;

    @GetMapping("/id")
    public ResponseEntity<Object> getRestauranteById(@PathVariable("id") UUID id) throws Exception {

        GetRestauranteByIdUseCase useCase = new GetRestauranteByIdUseCase(
                new RestauranteEntityRepository(this.restauranteRepository)
        );
        useCase.execute(id);

        return ResponseEntity.ok(id);
    }
}
