package com.mauricio.usuarioservice.feignclients;

import com.mauricio.usuarioservice.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
@RequestMapping(value = "/api/car")
public
interface CarFeignClient {

    @PostMapping
    Car save(@RequestBody Car car);

    @GetMapping(value = "/usuario/{usuarioId}")
    List<Car> getCars(@PathVariable(value = "usuarioId") int usuarioId);
}
