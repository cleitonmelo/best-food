package com.fiap.techchalleng.best_food.infra.repository;

import com.fiap.techchalleng.best_food.infra.model.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<ReservaModel, UUID> {
    Optional<ReservaModel> findByIdMesaAndDataReservaEqualsAndHoraReservaEqualsAndDataCancelamentoReservaIsNull(UUID idMesa, LocalDate dataReserva, LocalTime horaReserva);
}
