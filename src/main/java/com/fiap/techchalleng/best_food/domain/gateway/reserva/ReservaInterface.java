package com.fiap.techchalleng.best_food.domain.gateway.reserva;

import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.infra.model.ReservaModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface ReservaInterface {

    Reserva createReserva(Reserva reserva);

    Reserva cancelarReserva(Reserva reserva);

    Optional<ReservaModel> buscarReservaMesaDataHora(Reserva reserva);

    Optional<ReservaModel> buscarReserva(UUID id);


}
