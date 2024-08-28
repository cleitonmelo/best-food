package com.fiap.techchalleng.best_food.domain.usecase;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SearchRestauranteUseCase {

    private final RestauranteInterface restauranteInterface;

    public void execute() {
        try{
            List<Restaurante> listRestaurantes = restauranteInterface.buscaRestaurantes();
            System.out.println(listRestaurantes.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}
