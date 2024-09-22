package com.fiap.techchalleng.best_food.infra.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "mesas")
@Setter
@Getter
public class MesaModel {

    @Id
    private UUID id;

    private UUID idRestaurante;

    private int codigo;

    private int lugares;

    private boolean reservada;
}
