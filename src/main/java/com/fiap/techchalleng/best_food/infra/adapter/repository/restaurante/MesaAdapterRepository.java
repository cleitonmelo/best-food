package com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.MesaInterface;
import com.fiap.techchalleng.best_food.infra.model.MesaModel;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MesaAdapterRepository implements MesaInterface {
    private final MesaRepository mesaRepository;

    @Override
    public List<Mesa> getMesas() {
        List<MesaModel> mesaModelList = mesaRepository.findAll();

        return mesaModelList.stream()
                .map(this::toMesa)
                .collect(Collectors.toList());
    }

    @Override
    public List<Mesa> getMesasByIdRestaurante(UUID idRestaurante) {
        List<MesaModel> mesaModelList = mesaRepository.findByIdRestaurante(idRestaurante);

        return mesaModelList.stream()
                .map(this::toMesa)
                .collect(Collectors.toList());
    }

    @Override
    public Mesa findByIdMesa(UUID id) {
        MesaModel mesaModel = mesaRepository.findById(id).orElse(null);
        return mesaModel != null ? toMesa(mesaModel) : null;
    }

    @Override
    public List<Mesa> getMesasByReservada(Boolean reservada) {
        List<MesaModel> mesaModelList = mesaRepository.getMesasByReservada(reservada);

        return mesaModelList.stream()
                .map(this::toMesa) // Converte cada MesaModel para Mesa
                .collect(Collectors.toList());
    }

    @Override
    public Mesa createMesa(Mesa mesa) {
        MesaModel mesaModel = new MesaModel(mesa.id(), mesa.codigo(), mesa.lugares(), mesa.reservada());
        mesaModel = mesaRepository.save(mesaModel);
        return toMesa(mesaModel);
    }

    @Override
    public Mesa updateByIdMesa(Mesa mesa) {
        MesaModel mesaModel = mesaRepository.findById(mesa.id()).orElse(null);

        if (mesaModel != null) {
            mesaModel.setCodigo((mesa.codigo()));
            mesaModel.setLugares(mesa.lugares());
            mesaModel.setReservada(mesa.reservada());
            mesaModel = mesaRepository.save(mesaModel);

            return toMesa(mesaModel);
        }

        return null;
    }

    private List<Mesa> toMesaList(List<MesaModel> mesaModelList){
        return mesaModelList.stream()
                .map(this::toMesa)
                .collect(Collectors.toList());
    }

    private Mesa toMesa(MesaModel mesaModel){
        return Mesa.builder()
                .codigo(mesaModel.getCodigo())
                .lugares(mesaModel.getLugares())
                .reservada(mesaModel.isReservada())
                .build();
    }
    private void updateByIdMesa(UUID idMesa, MesaModel mesaModel){
        mesaRepository.save(mesaModel);

    }
}
