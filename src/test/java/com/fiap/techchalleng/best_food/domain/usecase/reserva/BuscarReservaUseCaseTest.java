package com.fiap.techchalleng.best_food.domain.usecase.reserva;

import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.BuscarRestauranteInput;
import com.fiap.techchalleng.best_food.domain.usecase.restaurante.BuscarRestauranteUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BuscarReservaUseCaseTest {

    @Mock
    private RestauranteInterface restauranteInterface;

    private BuscarRestauranteUseCase buscarRestauranteUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        buscarRestauranteUseCase = new BuscarRestauranteUseCase(restauranteInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirBuscarRestauranteUseCase(){

        BuscarRestauranteInput buscarRestauranteInput = BuscarRestauranteInput.builder()
                .nome("Restaurante de Teste")
                .build();

        buscarRestauranteUseCase.execute(buscarRestauranteInput);

        var output = buscarRestauranteUseCase.getOutput();
        assertThat(output.getOutputStatus().getCode()).isEqualTo(404);
        assertThat(output.getOutputStatus().getCodeName()).isEqualTo("NOT_FOUND");
    }

}
