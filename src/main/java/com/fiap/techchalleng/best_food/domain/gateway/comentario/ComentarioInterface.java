package com.fiap.techchalleng.best_food.domain.gateway.comentario;

import java.util.Optional;
import java.util.UUID;

import com.fiap.techchalleng.best_food.domain.entity.comentario.Comentario;
import com.fiap.techchalleng.best_food.domain.entity.reserva.Reserva;
import com.fiap.techchalleng.best_food.infra.model.ComentarioModel;

public interface ComentarioInterface {

    Reserva createComentario(Comentario comentario);

    Optional<ComentarioModel> buscarComentario(UUID id);

}
