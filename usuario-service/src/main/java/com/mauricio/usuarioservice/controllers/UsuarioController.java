package com.mauricio.usuarioservice.controllers;

import com.mauricio.usuarioservice.entities.Usuario;
import com.mauricio.usuarioservice.models.Car;
import com.mauricio.usuarioservice.models.Motorcycle;
import com.mauricio.usuarioservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/usuario")
public
class UsuarioController {

    @Autowired
    private
    UsuarioService usuarioService;

    @GetMapping
    public
    ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "id") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
        Usuario newUsuario = usuarioService.saveUsuario(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                  .buildAndExpand(newUsuario.getId()).toUri();
        return ResponseEntity.created(location).body(newUsuario);
    }

    @GetMapping(value = "/cars/{usuarioId}")
    public ResponseEntity<List<Car>> carList(@PathVariable(value = "usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = usuarioService.getCars(usuarioId);
        return ResponseEntity.ok(cars);
    }
    @GetMapping(value = "/motorcycle/{usuarioId}")
    public ResponseEntity<List<Motorcycle>> motorcycleList(@PathVariable(value = "usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Motorcycle> motorcycles = usuarioService.getMotorcycles(usuarioId);
        return ResponseEntity.ok(motorcycles);
    }
    @PostMapping("/car/{usuarioId}")
    public ResponseEntity<Car> saveCar(@PathVariable(value = "usuarioId") int usuarioId
            , @RequestBody Car car){
        Car newCar = usuarioService.saveCar(usuarioId, car);
        return ResponseEntity.ok(newCar);
    }

    @PostMapping("/motorcycle/{usuarioId}")
    public ResponseEntity<Motorcycle> saveMotorcycle(@PathVariable(value = "usuarioId") int usuarioId
            , @RequestBody Motorcycle motorcycle){
        Motorcycle newMotorcycle = usuarioService.saveMotorcycle(usuarioId, motorcycle);
        return ResponseEntity.ok(newMotorcycle);
    }
    @GetMapping(value = "/all-data/{usuarioId}")
    public ResponseEntity<Map<String, Object>> getAllVehicleData(@PathVariable(value =
            "usuarioId") int usuarioId){
        Map<String, Object> result = usuarioService.getUsuarioDataById(usuarioId);
        return ResponseEntity.ok(result);
    }

}
