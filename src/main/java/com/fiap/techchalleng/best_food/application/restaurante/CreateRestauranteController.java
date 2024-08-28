package com.fiap.techchalleng.best_food.application.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/restaurantes")
public class CreateRestauranteController {

    private RestauranteRepository restauranteRepository;

    @PostMapping
    public ResponseEntity<Object> saveRestaurante(@RequestBody Restaurante restaurante) {
        return ResponseEntity.ok().body(restaurante);
    }
}
