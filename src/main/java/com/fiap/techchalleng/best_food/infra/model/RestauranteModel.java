package com.fiap.techchalleng.best_food.infra.model;

import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import jakarta.persistence.Column;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private TipoCozinha tipoCozinha;

    private String logradouro;

    private String cidade;

    private String estado;

    private String cep;

    private String bairro;

    @Column(nullable = false)
    private Integer capacidade;



}
