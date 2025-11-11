package com.api.desafio.back.repository;

import com.api.desafio.back.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  // Método para buscar o usuário pelo email (nosso "username")
  Optional<Usuario> findByEmail(String email);
}