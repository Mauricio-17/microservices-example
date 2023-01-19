package com.mauricio.carservice.repositories;

import com.mauricio.carservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByUsuarioId(int usuarioId);
}
