package com.fiap.techchalleng.best_food.domain.usecase.reserva;

import com.fiap.techchalleng.best_food.domain.usecase.restaurante.CreateRestauranteUseCase;
import com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante.RestauranteAdapterRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;


public class CreateRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteAdapterRepository adapterRepository;

    private CreateRestauranteUseCase createRestauranteUseCase;
    AutoCloseable openMocks;

    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        createRestauranteUseCase = new CreateRestauranteUseCase(adapterRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


}
