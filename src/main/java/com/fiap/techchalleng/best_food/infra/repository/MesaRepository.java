package com.fiap.techchalleng.best_food.infra.repository;

import com.fiap.techchalleng.best_food.infra.model.MesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MesaRepository extends JpaRepository<MesaModel, UUID> {
}
