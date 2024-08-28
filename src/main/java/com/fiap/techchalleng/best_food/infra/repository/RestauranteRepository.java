package com.fiap.techchalleng.best_food.infra.repository;

import com.fiap.techchalleng.best_food.infra.model.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<RestauranteModel, Long> {

}
