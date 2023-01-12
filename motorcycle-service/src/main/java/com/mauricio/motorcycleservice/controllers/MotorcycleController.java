package com.mauricio.motorcycleservice.controllers;

import com.mauricio.motorcycleservice.entities.Motorcycle;
import com.mauricio.motorcycleservice.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/motorcycle")
public
class MotorcycleController {

    @Autowired
    private
    MotorcycleService motorcycleService;

    @GetMapping
    public
    ResponseEntity<List<Motorcycle>> getAllMotorcycles(){
        List<Motorcycle> motorcycles = motorcycleService.getAllMotorcycles();
        if (motorcycles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorcycles);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Motorcycle> getMotorcycle(@PathVariable(value = "id") int id){
        Motorcycle motorcycle = motorcycleService.getMotorcycleById(id);
        if (motorcycle == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motorcycle);
    }
    @PostMapping
    public ResponseEntity<Motorcycle> saveMotorcycle(@RequestBody Motorcycle motorcycle){
        Motorcycle newMotorcycle = motorcycleService.saveMotorcycle(motorcycle);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                  .buildAndExpand(newMotorcycle.getId()).toUri();
        return ResponseEntity.created(location).body(newMotorcycle);
    }
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<List<Motorcycle>> getMotorcyclesByUsuarioId(@PathVariable(value = "id") int usuarioId){
        List<Motorcycle> motorcycles = motorcycleService.getMotorcyclesByUsuarioId(usuarioId);
        if (motorcycles.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motorcycles);
    }

}
