package com.fiap.techchalleng.best_food.application.reserva;

import com.fiap.techchalleng.best_food.application.reserva.request.CreateReservaRequest;
import com.fiap.techchalleng.best_food.application.response.GenericResponse;
import com.fiap.techchalleng.best_food.application.response.PresenterResponse;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.reserva.CreateReservaInput;
import com.fiap.techchalleng.best_food.domain.output.reserva.CreateReservaOutput;
import com.fiap.techchalleng.best_food.domain.presenters.reserva.create.CreateReservaPresenter;
import com.fiap.techchalleng.best_food.domain.usecase.reserva.CreateReservaUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.reserva.ReservaAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import com.fiap.techchalleng.best_food.infra.repository.ReservaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reservas")
public class CreateReservaController {


    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateReservaRequest request) {
        OutputInterface outputInterface = this.getOutputInterface(request);

        if (outputInterface.getOutputStatus().getCode() != HttpStatus.CREATED.value()) {
            return new GenericResponse().response(outputInterface);
        }

        CreateReservaPresenter presenter = new CreateReservaPresenter((CreateReservaOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }

    private OutputInterface getOutputInterface(CreateReservaRequest request) {
        CreateReservaInput input = CreateReservaInput.builder()
                .idRestaurante(request.idRestaurante())
                .idMesa(request.idMesa())
                .nome(request.nome())
                .telefone(request.telefone())
                .qtdeLugares(request.qtdeLugares())
                .dataReserva(request.dataReserva())
                .horaReserva(request.horaReserva())
                .build();

        CreateReservaUseCase useCase = new CreateReservaUseCase(
                new ReservaAdapterRepository(reservaRepository, mesaRepository)
        );

        useCase.execute(input);

        return useCase.getOutput();

    }
}
