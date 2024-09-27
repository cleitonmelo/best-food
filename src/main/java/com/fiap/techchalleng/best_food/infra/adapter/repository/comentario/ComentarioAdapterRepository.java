package com.fiap.techchalleng.best_food.infra.adapter.repository.comentario;

import java.util.Optional;
import java.util.UUID;

import com.fiap.techchalleng.best_food.domain.entity.comentario.Comentario;
import com.fiap.techchalleng.best_food.domain.gateway.comentario.ComentarioInterface;
import com.fiap.techchalleng.best_food.infra.model.ComentarioModel;
import com.fiap.techchalleng.best_food.infra.repository.ComentarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComentarioAdapterRepository implements ComentarioInterface {

    private final ComentarioRepository comentarioRepository;

    @Override
    public Comentario createComentario(Comentario comentario) {

        UUID uuid = UUID.randomUUID();

        ComentarioModel model = ComentarioModel.builder()
                .id(uuid)
                .idReserva(comentario.getIdReserva())
                .comentario(comentario.getComentario())
                .dataComentario(comentario.getDataComentario())
                .horaComentario(comentario.getHoraComentario()).build();

        comentarioRepository.save(model);

        comentario.setId(uuid);

        return comentario;

    }

    @Override
    public Optional<ComentarioModel> buscarComentario(UUID id) {
        return comentarioRepository.findById(id);
    }

}
