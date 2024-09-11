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
@Table( name = "reservas")
@Getter
public class ReservaModel {

    @Id
    private UUID id;

    private UUID idRestaurante;

    private UUID idMesa;

    private String nome;

    private String telefone;

    private Integer qtdeLugares;

    private LocalDate dataReserva;

    private LocalTime horaReserva;

    private LocalDate dataCancelamentoReserva;

}
