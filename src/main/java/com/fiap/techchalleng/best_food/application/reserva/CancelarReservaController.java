package com.fiap.techchalleng.best_food.application.reserva;

import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterResponse;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.output.reserva.CancelarReservaOutput;
import com.fiap.techchalleng.best_food.domain.presenters.reserva.update.UpdateReservaPresenter;
import com.fiap.techchalleng.best_food.domain.usecase.reserva.CancelarReservaUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.reserva.ReservaAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import com.fiap.techchalleng.best_food.infra.repository.ReservaRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reservas")
public class CancelarReservaController {

    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> cancelarReserva(@PathVariable UUID uuid) {
        CancelarReservaUseCase useCase = new CancelarReservaUseCase(
                new ReservaAdapterRepository(reservaRepository, mesaRepository)
        );
        useCase.execute(uuid);
        OutputInterface outputInterface = useCase.getOutput();
        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        UpdateReservaPresenter presenter = new UpdateReservaPresenter((CancelarReservaOutput) outputInterface);
        return new PresenterResponse().response(presenter);

    }

}
