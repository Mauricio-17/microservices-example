package com.mauricio.motorcycleservice.service;

import com.mauricio.motorcycleservice.entities.Motorcycle;
import com.mauricio.motorcycleservice.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    public
    List<Motorcycle> getAllMotorcycles(){
        return motorcycleRepository.findAll();
    }
    public Motorcycle getMotorcycleById(int id){
        return motorcycleRepository.findById(id).orElse(null);
    }
    public Motorcycle saveMotorcycle(Motorcycle car){
        Motorcycle newCar = motorcycleRepository.save(car);
        return newCar;
    }
    public List<Motorcycle> getMotorcyclesByUsuarioId(int usuarioId){
        return motorcycleRepository.findByUsuarioId(usuarioId);
    }
}
