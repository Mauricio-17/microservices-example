package com.mauricio.motorcycleservice.repository;

import com.mauricio.motorcycleservice.entities.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public
interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {

    List<Motorcycle> findByUsuarioId(int usuarioId);

}
