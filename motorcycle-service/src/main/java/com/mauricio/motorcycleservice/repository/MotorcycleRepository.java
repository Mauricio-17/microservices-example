package com.mauricio.motorcycleservice.repository;

import com.mauricio.motorcycleservice.entities.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {

    List<Motorcycle> findByUsuarioId(int usuarioId);

}
