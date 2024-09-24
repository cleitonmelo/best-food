package com.fiap.techchalleng.best_food.infra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.techchalleng.best_food.infra.model.ComentarioModel;

public interface ComentarioRepository extends JpaRepository<ComentarioModel, UUID> {
    Optional<ComentarioModel> findById(UUID id);
}
