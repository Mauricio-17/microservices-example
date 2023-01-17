package com.mauricio.usuarioservice.feignclients;

import com.mauricio.usuarioservice.models.Car;
import com.mauricio.usuarioservice.models.Motorcycle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "motorcycle-service")
@RequestMapping(value = "/api/motorcycle")
public
interface MotorcycleFeignClient {

    @PostMapping
    Motorcycle save(@RequestBody Motorcycle motorcycle);

    @GetMapping(value = "/usuario/{usuarioId}")
    List<Motorcycle> getMotorcycles(@PathVariable(value = "usuarioId") int usuarioId);
}
