package com.fiap.techchalleng.best_food.infra.adapter.repository.reserva;

import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.domain.gateway.reserva.ReservaInterface;
import com.fiap.techchalleng.best_food.infra.model.ReservaModel;
import com.fiap.techchalleng.best_food.infra.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ReservaAdapterRepository implements ReservaInterface {

    private final ReservaRepository reservaRepository;

    @Override
    public Reserva createReserva(Reserva reserva) {

        UUID uuid = UUID.randomUUID();

        ReservaModel model = ReservaModel.builder()
                .id(uuid)
                .idRestaurante(reserva.getIdRestaurante())
                .idMesa(reserva.getIdMesa())
                .nome(reserva.getNome())
                .telefone(reserva.getTelefone())
                .qtdeLugares(reserva.getQtdeLugares())
                .dataReserva(reserva.getDataReserva())
                .horaReserva(reserva.getHoraReserva()).build();

        reservaRepository.save(model);

        reserva.setId(uuid);

        return reserva;

    }

    @Override
    public Reserva cancelarReserva(Reserva reserva) {
        ReservaModel model = ReservaModel.builder()
                .id(reserva.getId())
                .idRestaurante(reserva.getIdRestaurante())
                .idMesa(reserva.getIdMesa())
                .nome(reserva.getNome())
                .telefone(reserva.getTelefone())
                .qtdeLugares(reserva.getQtdeLugares())
                .dataReserva(reserva.getDataReserva())
                .horaReserva(reserva.getHoraReserva())
                .dataCancelamentoReserva(LocalDate.now())
                .build();

        reservaRepository.save(model);

        return reserva;
    }

    @Override
    public Optional<ReservaModel> buscarReservaMesaDataHora(Reserva reserva) {
        Optional<ReservaModel> mesaReservada = reservaRepository.findByIdMesaAndDataReservaEqualsAndHoraReservaEqualsAndDataCancelamentoReservaIsNull(reserva.getIdMesa(),reserva.getDataReserva(), reserva.getHoraReserva());
        return mesaReservada;
    }

    @Override
    public Optional<ReservaModel> buscarReserva(UUID id) {
        Optional<ReservaModel> reserva = reservaRepository.findById(id);
        return reserva;
    }

}
