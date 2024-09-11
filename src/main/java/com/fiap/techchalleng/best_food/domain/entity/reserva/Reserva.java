package com.fiap.techchalleng.best_food.domain.entity.reserva;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Data
public class Reserva  {

    private UUID id;

    private UUID idRestaurante;

    private UUID idMesa;

    private String nome;

    private String telefone;

    private Integer qtdeLugares;

    private LocalDate dataReserva;

    private LocalTime horaReserva;

    private LocalDate dataCancelamentoReserva;

    public Reserva(UUID id, UUID idRestaurante, UUID idMesa, String nome, String telefone, Integer qtdeLugares, LocalDate dataReserva, LocalTime horaReserva, LocalDate dataCancelamentoReserva)  {

        if (telefone == null || !telefone.matches("(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")) {
            throw new IllegalArgumentException("Telefone fora do padrão (XX)XXXXX-XXXX");
        }

        if( qtdeLugares <= 0 || qtdeLugares > 99){
            throw new IllegalArgumentException("A quantidade de lugares deve estar entre 1 e 99");
        }

        this.id = id;
        this.idRestaurante = idRestaurante;
        this.idMesa = idMesa;
        this.nome = nome;
        this.telefone = telefone;
        this.qtdeLugares = qtdeLugares;
        this.dataReserva = dataReserva;
        this.horaReserva = horaReserva;
        this.dataCancelamentoReserva = dataCancelamentoReserva;

    }

}
