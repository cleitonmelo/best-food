package com.fiap.techchalleng.best_food.domain.usecase;


import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class GetRestauranteByIdUseCase {

    private final RestauranteInterface restauranteInterface;

    public void execute(UUID id) throws Exception {
        try{
            Restaurante search = this.restauranteInterface.getRestauranteById(id);
            if(search == null){
                System.out.println("Restaurante not found");
            }
            System.out.println(search);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
