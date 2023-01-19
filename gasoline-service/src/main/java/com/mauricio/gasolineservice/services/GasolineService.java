package com.mauricio.gasolineservice.services;

import com.mauricio.gasolineservice.entities.Gasoline;
import com.mauricio.gasolineservice.repositories.GasolineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class GasolineService {

    @Autowired
    private
    GasolineRepository gasolineRepository;

    public
    List<Gasoline> getAllGasoline(){
        return gasolineRepository.findAll();
    }
    public Gasoline getGasolineById(int id){
        return gasolineRepository.findById(id).orElse(null);
    }
    public Gasoline saveGasoline(Gasoline gasoline){
        Gasoline newGasoline = gasolineRepository.save(gasoline);
        return newGasoline;
    }
    public List<Gasoline> getGasolineByUsuarioId(int usuarioId){
        return gasolineRepository.findByUsuarioId(usuarioId);
    }

}
