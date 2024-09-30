package com.fiap.techchalleng.best_food.infra.adapter.repository.restaurante;

import com.fiap.techchalleng.best_food.domain.entity.restaurante.Mesa;
import com.fiap.techchalleng.best_food.domain.entity.restaurante.Restaurante;
import com.fiap.techchalleng.best_food.domain.enums.restaurante.TipoCozinha;
import com.fiap.techchalleng.best_food.domain.gateway.restaurante.RestauranteInterface;
import com.fiap.techchalleng.best_food.infra.model.MesaModel;
import com.fiap.techchalleng.best_food.infra.model.RestauranteModel;
import com.fiap.techchalleng.best_food.infra.repository.MesaRepository;
import com.fiap.techchalleng.best_food.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RestauranteAdapterRepository implements RestauranteInterface {

    private final RestauranteRepository repository;

    private final MesaRepository mesaRepository;

    @Override
    public List<Restaurante> getRestaurantes() {

        List<RestauranteModel> restauranteModelList = repository.findAll();

        return restauranteModelList.stream()
                .map(restauranteModel -> {
                    List<MesaModel> mesaModelList = mesaRepository.findByIdRestaurante(restauranteModel.getId());
                    return toRestaurante(restauranteModel, mesaModelList);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Restaurante getRestauranteById(UUID id) {
        return null;
    }

    @Override
    public List<Restaurante> getRestaurantesByName(String nome) {

        List<RestauranteModel> listRestauranteModel = repository.findByName(nome);

        return listRestauranteModel.stream()
                .map(restauranteModel -> {
                    List<MesaModel> listMesaModel = mesaRepository.findByIdRestaurante(restauranteModel.getId());
                    return toRestaurante(restauranteModel,listMesaModel);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurante> getRestaurantesByBairro(String bairro) {

        List<RestauranteModel> restauranteModelList = repository.findByBairro(bairro);

        return restauranteModelList.stream()
                .map(restauranteModel -> {
                    List<MesaModel> mesaModelList = mesaRepository.findByIdRestaurante(restauranteModel.getId());
                    return toRestaurante(restauranteModel,mesaModelList);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurante> getRestaurantesByTipoCozinha(TipoCozinha tipoCozinha) {

        List<RestauranteModel> restauranteModelList = repository.findByTipoCozinha(tipoCozinha);

        return restauranteModelList.stream()
                .map(restauranteModel -> {
                    List<MesaModel> mesaModelList = mesaRepository.findByIdRestaurante(restauranteModel.getId());
                    return toRestaurante(restauranteModel, mesaModelList);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Restaurante createRestaurante(Restaurante restaurante, List<Mesa> mesas) {
        UUID uuid = UUID.randomUUID();
        RestauranteModel model = RestauranteModel.builder()
                .id(uuid)
                .name(restaurante.nome())
                .tipoCozinha(restaurante.tipoCozinha())
                .logradouro(restaurante.logradouro())
                .bairro(restaurante.bairro())
                .cidade(restaurante.cidade())
                .estado(restaurante.estado())
                .cep(restaurante.cep())
                .build();

        repository.save(model);
        this.saveMesasByRestaurante(uuid,mesas);

        return Restaurante.builder()
                .id(uuid)
                .nome(restaurante.nome())
                .capacidade(restaurante.capacidade())
                .tipoCozinha(restaurante.tipoCozinha())
                .logradouro(restaurante.logradouro())
                .bairro(restaurante.bairro())
                .cidade(restaurante.cidade())
                .estado(restaurante.estado())
                .cep(restaurante.cep())
                .mesas(mesas)
                .build();
    }

    private void saveMesasByRestaurante(UUID restaurante, List<Mesa> mesas){
        for (Mesa mesa : mesas) {
            MesaModel mesaModel = MesaModel.builder()
                    .id(UUID.randomUUID())
                    .idRestaurante(restaurante)
                    .codigo(mesa.codigo())
                    .lugares(mesa.lugares())
                    .reservada(mesa.reservada())
                    .build();

            mesaRepository.save(mesaModel);
        }
    }

    private Restaurante toRestaurante(RestauranteModel restauranteModel, List<MesaModel> listMesaModelRestaurante){
        return Restaurante.builder()
                .id(restauranteModel.getId())
                .nome(restauranteModel.getName())
                .capacidade(calculateTotalCapacity(listMesaModelRestaurante))
                .tipoCozinha(restauranteModel.getTipoCozinha())
                .logradouro(restauranteModel.getLogradouro())
                .bairro(restauranteModel.getBairro())
                .cidade(restauranteModel.getCidade())
                .estado(restauranteModel.getEstado())
                .cep(restauranteModel.getCep())
                .mesas(toMesaList(listMesaModelRestaurante))
                .build();
    }

    private Integer calculateTotalCapacity(List<MesaModel> mesas){
        return mesas.stream()
                .mapToInt(MesaModel::getLugares)
                .sum();
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
