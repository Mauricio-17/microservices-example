package com.mauricio.usuarioservice.controllers;

import com.mauricio.usuarioservice.entities.Usuario;
import com.mauricio.usuarioservice.models.Car;
import com.mauricio.usuarioservice.models.Gasoline;
import com.mauricio.usuarioservice.models.Motorcycle;
import com.mauricio.usuarioservice.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetCars")
    @GetMapping(value = "/cars/{usuarioId}")
    public ResponseEntity<List<Car>> carList(@PathVariable(value = "usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = usuarioService.getCars(usuarioId);
        return ResponseEntity.ok(cars);
    }
    @CircuitBreaker(name = "motorcyclesCB", fallbackMethod = "fallBackGetMotorcycles")
    @GetMapping(value = "/motorcycle/{usuarioId}")
    public ResponseEntity<List<Motorcycle>> motorcycleList(@PathVariable(value = "usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Motorcycle> motorcycles = usuarioService.getMotorcycles(usuarioId);
        return ResponseEntity.ok(motorcycles);
    }
    @CircuitBreaker(name = "gasolineCB", fallbackMethod = "fallBackGetGasoline")
    @GetMapping(value = "/gasoline/{usuarioId}")
    public ResponseEntity<List<Gasoline>> gasolineList(@PathVariable(value =
            "usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Gasoline> gasoline = usuarioService.getGasoline(usuarioId);
        return ResponseEntity.ok(gasoline);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackSaveCar")
    @PostMapping("/car/{usuarioId}")
    public ResponseEntity<Car> saveCar(@PathVariable(value = "usuarioId") int usuarioId
            , @RequestBody Car car){
        Car newCar = usuarioService.saveCar(usuarioId, car);
        return ResponseEntity.ok(newCar);
    }
    @CircuitBreaker(name = "motorcyclesCB", fallbackMethod = "fallBackSaveMotorcycle")
    @PostMapping("/motorcycle/{usuarioId}")
    public ResponseEntity<Motorcycle> saveMotorcycle(@PathVariable(value = "usuarioId") int usuarioId
            , @RequestBody Motorcycle motorcycle){
        Motorcycle newMotorcycle = usuarioService.saveMotorcycle(usuarioId, motorcycle);
        return ResponseEntity.ok(newMotorcycle);
    }
    @CircuitBreaker(name = "gasolineCB", fallbackMethod = "fallBackSaveGasoline")
    @PostMapping("/gasoline/{usuarioId}")
    public ResponseEntity<Gasoline> saveGasoline(@PathVariable(value = "usuarioId") int usuarioId
            , @RequestBody Gasoline gasoline){
        Gasoline newGasoline = usuarioService.saveGasoline(usuarioId, gasoline);
        return ResponseEntity.ok(newGasoline);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
    @GetMapping(value = "/all-data/{usuarioId}")
    public ResponseEntity<Map<String, Object>> getAllVehicleData(@PathVariable(value =
            "usuarioId") int usuarioId){
        Map<String, Object> result = usuarioService.getUsuarioDataById(usuarioId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<List<Car>> fallBackGetCars(@PathVariable("usuarioId") int id,
            RuntimeException exception){
        return new ResponseEntity(String.format("El usuario: %d tiene sus carros en el "
                                                + "taller", id), HttpStatus.OK);
    }
    public ResponseEntity<Car> fallBackSaveCar(@PathVariable("usuarioId") int id,
            @RequestBody Car car, RuntimeException exception){
        return new ResponseEntity(String.format("El usuario: %d no tiene dinero para "
                                                + "los carros", id), HttpStatus.OK);
    }

    public ResponseEntity<List<Motorcycle>> fallBackGetMotorcycles(@PathVariable("usuarioId") int id, RuntimeException exception){
        return new ResponseEntity(String.format("El usuario: %d tiene sus motors en el "
                                                + "taller", id), HttpStatus.OK);
    }
    public ResponseEntity<Motorcycle> fallBackSaveMotorcycle(@PathVariable(
            "usuarioId") int id, @RequestBody Motorcycle motorcycle,
            RuntimeException exception){
        return new ResponseEntity(String.format("El usuario: %d no tiene dinero para "
                                                + "las motos", id), HttpStatus.OK);
    }

    public ResponseEntity<List<Gasoline>> fallBackGetGasoline(@PathVariable("usuarioId") int id, RuntimeException exception){
        return new ResponseEntity(String.format("El usuario: %d se le agotó la gasolina.",
                                                id),
                                  HttpStatus.OK);
    }
    public ResponseEntity<Gasoline> fallBackSaveGasoline(@PathVariable(
            "usuarioId") int id, @RequestBody Gasoline gasoline,
            RuntimeException exception){
        return new ResponseEntity(String.format("El usuario: %d no tiene dinero para "
                                                + "comprar gasoline :(", id),
                                  HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> fallBackGetAll(@PathVariable(
            "usuarioId") int id,
            RuntimeException exception){
        return new ResponseEntity(String.format("El usuario: %d tiene los vehículos en "
                                                + "el taller", id), HttpStatus.OK);
    }

}
