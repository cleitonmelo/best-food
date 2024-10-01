package com.fiap.techchalleng.best_food.domain.usecase.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.MesaInterface;
import com.fiap.techchalleng.best_food.domain.generic.output.OutputInterface;
import com.fiap.techchalleng.best_food.domain.input.restaurante.BuscarMesaInput;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BuscarMesaUseCaseTest {

    @Mock
    private MesaInterface mesaInterface;

    private BuscarMesaUseCase buscarMesaUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        buscarMesaUseCase = new BuscarMesaUseCase(mesaInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    public void deveBuscarMesaUseCase(){

        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas.add(Mesa.builder().codigo(1).lugares(4).build());
        mesas.add(Mesa.builder().codigo(2).lugares(4).build());
        mesas.add(Mesa.builder().codigo(3).lugares(4).build());

        when(mesaInterface.getMesasByIdRestaurante(any())).thenReturn(mesas);

        buscarMesaUseCase.execute(BuscarMesaInput.builder().build());

        OutputInterface output = buscarMesaUseCase.getOutput();
        System.out.println(output);

        assertThat(output.getOutputStatus().getCode()).isEqualTo(200);
        AssertionsForClassTypes.assertThat(output.getOutputStatus().getCodeName()).isEqualTo("OK");
    }

}
