package com.mauricio.carservice.repositories;

import com.mauricio.carservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public
interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByUsuarioId(int usuarioId);
}
