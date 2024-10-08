package com.fiap.techchalleng.best_food.domain.input.reserva;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record CreateReservaInput (
        UUID idRestaurante,
        UUID idMesa,
        String nome,
        String telefone,
        Integer qtdeLugares,
        LocalDate dataReserva,
        LocalTime horaReserva
){}