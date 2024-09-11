package com.fiap.techchalleng.best_food.domain.usecase.reserva;

import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.domain.gateway.reserva.ReservaInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import com.fiap.techchalleng.best_food.domain.input.reserva.CreateReservaInput;
import com.fiap.techchalleng.best_food.domain.output.reserva.CreateReservaOutput;
import com.fiap.techchalleng.best_food.infra.model.ReservaModel;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class CreateReservaUseCase {

    private final ReservaInterface reservaRepository;
    //private final RestauranteInterface restauranteRepository;
    private OutputInterface output;

    public void execute(@Valid CreateReservaInput input) {
        Reserva reserva = Reserva.builder()
                .idRestaurante(input.idRestaurante())
                .idMesa(input.idMesa())
                .nome(input.nome())
                .telefone(input.telefone())
                .qtdeLugares(input.qtdeLugares())
                .dataReserva(input.dataReserva())
                .horaReserva(input.horaReserva())
                .build();

        Optional<ReservaModel> mesaReservada = reservaRepository.buscarReservaMesaDataHora(reserva);

        if(mesaReservada.isPresent()){
            throw new IllegalArgumentException("A mesa já possui reserva para a data e hora informada");
        }

        Reserva data = reservaRepository.createReserva(reserva);
        //Restaurante restaurante = restauranteRepository.getRestauranteById(input.idRestaurante());

        output = new CreateReservaOutput(
                data,
                new OutputStatus(201, "Created", "Reserva criada")
        );

    }

}
