package com.fiap.techchalleng.best_food.domain.usecase.reserva;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputStatus;
import com.fiap.techchalleng.best_food.domain.input.restaurante.CreateRestauranteInput;
import com.fiap.techchalleng.best_food.domain.output.restaurante.CreateRestauranteOutput;
import com.fiap.techchalleng.best_food.domain.usecase.restaurante.CreateRestauranteUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.RestauranteAdapterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class CreateRestauranteUseCaseTest {

    @Mock
    private RestauranteInterface repository;

    @Mock
    private RestauranteAdapterRepository adapterRepository;

    private CreateRestauranteUseCase createRestauranteUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        createRestauranteUseCase = new CreateRestauranteUseCase(adapterRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarRestaurante(){

        var input = CreateRestauranteInput.builder()
                .nome("Restaurante de Teste")
                .tipoCozinha(TipoCozinha.AFRICANA)
                .capacidade(100)
                .build();

        var restaurante = Restaurante.builder()
                            .nome(input.nome())
                            .tipoCozinha(input.tipoCozinha())
                            .capacidade(input.capacidade())
                            .build();

        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas.add(Mesa.builder().codigo(1).lugares(6).reservada(false).build());
        mesas.add(Mesa.builder().codigo(2).lugares(6).reservada(false).build());
        mesas.add(Mesa.builder().codigo(3).lugares(6).reservada(false).build());

        when(adapterRepository.createRestaurante(restaurante,mesas)).thenReturn(restaurante);

        createRestauranteUseCase.execute(input);
        Assertions.assertEquals(createRestauranteUseCase.getOutput().getOutputStatus().getCode(),HttpStatus.CREATED.value());

    }


}
