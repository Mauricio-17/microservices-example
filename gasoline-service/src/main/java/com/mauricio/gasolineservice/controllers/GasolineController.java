package com.mauricio.gasolineservice.controllers;

import com.mauricio.gasolineservice.entities.Gasoline;
import com.mauricio.gasolineservice.services.GasolineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gasoline")
public
class GasolineController {

    @Autowired
    private
    GasolineService gasolineService;

    @GetMapping
    public
    ResponseEntity<List<Gasoline>> getAllGasoline(){
        List<Gasoline> gasoline = gasolineService.getAllGasoline();
        if (gasoline.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(gasoline);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Gasoline> getGasoline(@PathVariable(value = "id") int id){
        Gasoline gasoline = gasolineService.getGasolineById(id);
        if (gasoline == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gasoline);
    }
    @PostMapping
    public ResponseEntity<Gasoline> saveGasoline(@RequestBody Gasoline gasoline){
        Gasoline newGasoline = gasolineService.saveGasoline(gasoline);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                  .buildAndExpand(newGasoline.getId()).toUri();
        return ResponseEntity.created(location).body(newGasoline);
    }
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<List<Gasoline>> getGasolineByUsuarioId(@PathVariable(value = "id") int usuarioId){
        List<Gasoline> gasoline = gasolineService.getGasolineByUsuarioId(usuarioId);
        if (gasoline.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(gasoline);
    }
}
