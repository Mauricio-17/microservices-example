package com.mauricio.usuarioservice.repositories;

import com.mauricio.usuarioservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
