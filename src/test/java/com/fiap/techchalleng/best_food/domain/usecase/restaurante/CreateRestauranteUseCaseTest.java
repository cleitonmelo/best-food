package com.fiap.techchalleng.best_food.domain.usecase.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.CreateRestauranteInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class CreateRestauranteUseCaseTest {

    @Mock
    private RestauranteInterface restauranteInterface;

    private CreateRestauranteUseCase createRestauranteUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        createRestauranteUseCase = new CreateRestauranteUseCase(restauranteInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarRestauranteUseCase(){

        var restaurante = Restaurante.builder()
                .nome("Restaurante de Teste")
                .tipoCozinha(TipoCozinha.BRASILEIRA)
                .build();

        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas.add(Mesa.builder().codigo(1).lugares(4).build());
        mesas.add(Mesa.builder().codigo(2).lugares(4).build());
        mesas.add(Mesa.builder().codigo(3).lugares(4).build());

        when(restauranteInterface.createRestaurante(restaurante,mesas)).thenReturn(restaurante);

        var restauranteInput = CreateRestauranteInput.builder()
                .nome(restaurante.nome())
                .tipoCozinha(restaurante.tipoCozinha())
                .mesas(mesas)
                .build();

        createRestauranteUseCase.execute(restauranteInput);

        var output = createRestauranteUseCase.getOutput();
        assertThat(output.getOutputStatus().getCode()).isEqualTo(201);
        assertThat(output.getOutputStatus().getCodeName()).isEqualTo("CREATED");
    }


}
