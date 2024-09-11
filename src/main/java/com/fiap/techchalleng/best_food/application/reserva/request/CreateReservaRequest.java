package com.fiap.techchalleng.best_food.application.reserva.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateReservaRequest(
        UUID id,
        UUID idRestaurante,
        UUID idMesa,
        String nome,
        String telefone,
        Integer qtdeLugares,
        LocalDate dataReserva,
        LocalTime horaReserva

) {
}
