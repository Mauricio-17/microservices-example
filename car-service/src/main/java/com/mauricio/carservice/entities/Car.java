package com.mauricio.carservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public
class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marca;
    private String modelo;
    private int usuarioId;

}
