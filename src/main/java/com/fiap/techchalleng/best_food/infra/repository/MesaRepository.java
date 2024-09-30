package com.fiap.techchalleng.best_food.infra.repository;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.infra.model.MesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MesaRepository extends JpaRepository<MesaModel, UUID> {
    List<MesaModel> findByIdRestaurante(UUID idRestaurante);
    //List<MesaModel> findByIdMesa(UUID idMesa);
//    MesaModel updateByIdMesa(UUID idMesa, MesaModel mesa);
//    MesaModel createMesa(MesaModel mesa);
    List<MesaModel> getMesasByReservada(Boolean reservada);
}
