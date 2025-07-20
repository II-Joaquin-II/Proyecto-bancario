package com.banco.interbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banco.interbank.model.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    Optional<Usuario>findByEmailAndPassword(String email, String password);
    Optional<Usuario> findByDni(String dni);
    Optional<Usuario> findById(Long id);

}
