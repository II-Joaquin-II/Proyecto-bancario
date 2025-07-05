package com.banco.interbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banco.interbank.model.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long>{

}
