package com.fiap.techchalleng.best_food.infra.repository;

import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import com.fiap.techchalleng.best_food.infra.model.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteModel, UUID> {

    List<RestauranteModel> findByName(String name);

    List<RestauranteModel> findByBairro(String bairro);

    List<RestauranteModel> findByTipoCozinha(TipoCozinha tipoCozinha);

}
