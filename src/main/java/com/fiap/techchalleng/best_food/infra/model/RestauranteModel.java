package com.fiap.techchalleng.best_food.infra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="restaurantes")
public class RestauranteModel {

    @Id
    private UUID id;

    private String name;

}
