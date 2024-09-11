package com.fiap.techchalleng.best_food.domain.usecase.reserva;

import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.domain.gateway.reserva.ReservaInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import com.fiap.techchalleng.best_food.domain.output.reserva.CancelarReservaOutput;
import com.fiap.techchalleng.best_food.domain.usecase.base.BaseUseCase;
import com.fiap.techchalleng.best_food.infra.model.ReservaModel;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Getter
@Setter
@RequiredArgsConstructor
public class CancelarReservaUseCase extends BaseUseCase {

    private final ReservaInterface reservaRepository;
    private OutputInterface output;

    public void execute(UUID uuid) {

        Optional<ReservaModel> reservaModel = reservaRepository.buscarReserva(uuid);

        if (reservaModel.isEmpty()) {
            throw new IllegalArgumentException("Reserva não localizada");
        }

        Reserva reserva = Reserva.builder()
                .id(reservaModel.get().getId())
                .idRestaurante(reservaModel.get().getIdRestaurante())
                .idMesa(reservaModel.get().getIdMesa())
                .nome(reservaModel.get().getNome())
                .telefone(reservaModel.get().getTelefone())
                .qtdeLugares(reservaModel.get().getQtdeLugares())
                .dataReserva(reservaModel.get().getDataReserva())
                .horaReserva(reservaModel.get().getHoraReserva())
                .dataCancelamentoReserva(LocalDate.now())
                .build();

        Reserva data = reservaRepository.cancelarReserva(reserva);

        output = new CancelarReservaOutput(
                data,
                this.getStatusCodeOutput("Reserva cancelada com sucesso", HttpStatus.OK)
        );

    }

}
