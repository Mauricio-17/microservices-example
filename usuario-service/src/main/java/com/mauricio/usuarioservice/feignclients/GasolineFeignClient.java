package com.mauricio.usuarioservice.feignclients;

import com.mauricio.usuarioservice.models.Gasoline;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "gasoline-service")
@RequestMapping(value = "/api/gasoline")
public
interface GasolineFeignClient {

    @PostMapping
    Gasoline save(@RequestBody Gasoline gasoline);
    @GetMapping(value = "/usuario/{usuarioId}")
    List<Gasoline> getGasoline(@PathVariable(value = "usuarioId") int usuarioId);
}
