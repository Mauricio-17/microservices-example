package com.mauricio.gasolineservice.repositories;

import com.mauricio.gasolineservice.entities.Gasoline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface GasolineRepository extends JpaRepository<Gasoline, Integer> {

    List<Gasoline> findByUsuarioId(int usuarioId);
}
