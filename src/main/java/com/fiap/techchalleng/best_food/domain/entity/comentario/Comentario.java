package com.fiap.techchalleng.best_food.domain.entity.comentario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Comentario {

    private UUID id;

    private UUID idReserva;

    private String comentario;

    private LocalDate dataComentario;

    private LocalTime horaComentario;

    public Comentario(UUID id, UUID idReserva, String comentario, LocalDate dataComentario, LocalTime horaComentario)  {

        this.id = id;
        this.idReserva = idReserva;
        this.comentario = comentario;
        this.dataComentario = dataComentario;
        this.horaComentario = horaComentario;
        
    }
	
	
}
