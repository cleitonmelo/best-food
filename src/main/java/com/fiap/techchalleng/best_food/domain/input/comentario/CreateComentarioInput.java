package com.fiap.techchalleng.best_food.domain.input.comentario;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record CreateComentarioInput (
        UUID idReserva,
        String comentario,
        LocalDate dataComentario,
        LocalTime horaComentario
){}