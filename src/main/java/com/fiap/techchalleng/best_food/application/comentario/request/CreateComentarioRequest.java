package com.fiap.techchalleng.best_food.application.comentario.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateComentarioRequest(
        UUID id,
        UUID idReserva,
        String comentario,
        LocalDate dataComentario,
        LocalTime horaComentario
) {}
