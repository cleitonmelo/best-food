package com.fiap.techchalleng.best_food.domain.usecase.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.BuscarRestauranteInput;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BuscarRestauranteUseCaseTest {

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
    public void deveBuscarRestauranteUseCase(){

        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas.add(Mesa.builder().codigo(1).lugares(4).build());
        mesas.add(Mesa.builder().codigo(2).lugares(4).build());
        mesas.add(Mesa.builder().codigo(3).lugares(4).build());

        var restaurante = Restaurante.builder()
                .nome("Restaurante de Teste")
                .tipoCozinha(TipoCozinha.BRASILEIRA)
                .mesas(mesas)
                .build();

        when(restauranteInterface.getRestaurantes()).thenReturn(List.of(restaurante));

        buscarRestauranteUseCase.execute(BuscarRestauranteInput.builder().build());

        OutputInterface output = buscarRestauranteUseCase.getOutput();
        System.out.println(output);

        assertThat(output.getOutputStatus().getCode()).isEqualTo(200);
        AssertionsForClassTypes.assertThat(output.getOutputStatus().getCodeName()).isEqualTo("OK");
    }

}
