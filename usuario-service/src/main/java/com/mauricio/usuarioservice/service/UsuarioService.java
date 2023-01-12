package com.mauricio.usuarioservice.service;

import com.mauricio.usuarioservice.entities.Usuario;
import com.mauricio.usuarioservice.feignclients.CarFeignClient;
import com.mauricio.usuarioservice.feignclients.MotorcycleFeignClient;
import com.mauricio.usuarioservice.models.Car;
import com.mauricio.usuarioservice.models.Motorcycle;
import com.mauricio.usuarioservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private
    RestTemplate restTemplate;
    @Autowired
    private
    CarFeignClient carFeignClient;
    @Autowired
    private
    MotorcycleFeignClient motorcycleFeignClient;
    public
    List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario saveUsuario(Usuario usuario){
        Usuario newUsuario = usuarioRepository.save(usuario);
        return newUsuario;
    }


    public List<Car> getCars(int usuarioId){
        List<Car> cars = restTemplate.getForObject(String.format("http://localhost:8002"
                                                                 + "/api/car/usuario/%d", usuarioId), List.class);
        return cars;
    }
    public List<Motorcycle> getMotorcycles(int usuarioId){
        List<Motorcycle> motorcycles = restTemplate.getForObject(String.format("http://localhost:8003/api/motorcycle/usuario/%d", usuarioId), List.class);
        return motorcycles;
    }
    public Car saveCar(int usuarioId, Car car){
        car.setUsuarioId(usuarioId);
        Car newCar = carFeignClient.save(car);
        return newCar;
    }
    public Motorcycle saveMotorcycle(int usuarioId, Motorcycle motorcycle){
        motorcycle.setUsuarioId(usuarioId);
        Motorcycle newMotorcycle = motorcycleFeignClient.save(motorcycle);
        return newMotorcycle;
    }
    public Map<String, Object> getUsuarioDataById(int usuarioId){
        Map<String, Object> result = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario == null){
            result.put("Mensaje", "El usuario no existe");
            return result;
        }
        result.put("Usuario", usuario);
        List<Car> cars = carFeignClient.getCars(usuarioId);
        if (cars.isEmpty()){
            result.put("Carros", "El usuario no tiene carros.");
        }
        else{
            result.put("Carros", cars);
        }

        List<Motorcycle> motorcycles = motorcycleFeignClient.getMotorcycles(usuarioId);
        if (motorcycles.isEmpty()){
            result.put("Motos", "El usuario no tiene motos.");
        }
        else{
            result.put("Motos", motorcycles);
        }
        return result;
    }
}
