package com.fiap.techchalleng.best_food.domain.gateway.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import java.util.List;
import java.util.UUID;

public interface MesaInterface {
    List<Mesa> getMesas();
    Mesa findByIdMesa(UUID id);
    List<Mesa> getMesasByReservada(Boolean reservada);
    Mesa createMesa(Mesa mesa);
    Mesa updateByIdMesa(Mesa mesa);
}
