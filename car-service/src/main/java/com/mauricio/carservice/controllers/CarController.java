package com.mauricio.carservice.controllers;

import com.mauricio.carservice.entities.Car;
import com.mauricio.carservice.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/car")
public
class CarController {

    @Autowired
    private
    CarService carService;

    @GetMapping
    public
    ResponseEntity<List<Car>> getAllCars(){
        List<Car> usuarios = carService.getAllCars();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Car> getCar(@PathVariable(value = "id") int id){
        Car car = carService.getCarById(id);
        if (car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }
    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car usuario){
        Car newCar = carService.saveCar(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                  .buildAndExpand(newCar.getId()).toUri();
        return ResponseEntity.created(location).body(newCar);
    }
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<List<Car>> getCarsByUsuarioId(@PathVariable(value = "id") int usuarioId){
        List<Car> cars = carService.getCarsByUsuarioId(usuarioId);
        if (cars.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cars);
    }
}
