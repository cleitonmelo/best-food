package com.fiap.techchalleng.best_food.infra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "comentarios")
@Getter
public class ComentarioModel {

    @Id
    private UUID id;

    private UUID idReserva;

    private String comentario;

    private LocalDate dataComentario;

    private LocalTime horaComentario;

}
