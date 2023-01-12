package com.mauricio.carservice.services;

import com.mauricio.carservice.entities.Car;
import com.mauricio.carservice.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class CarService {

    @Autowired
    private CarRepository carRepository;

    public
    List<Car> getAllCars(){
        return carRepository.findAll();
    }
    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }
    public Car saveCar(Car car){
        Car newCar = carRepository.save(car);
        return newCar;
    }
    public List<Car> getCarsByUsuarioId(int usuarioId){
        return carRepository.findByUsuarioId(usuarioId);
    }
}
