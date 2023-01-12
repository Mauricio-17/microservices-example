package com.mauricio.usuarioservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public
class Car {

    private String marca;
    private String modelo;
    private int usuarioId;
}
