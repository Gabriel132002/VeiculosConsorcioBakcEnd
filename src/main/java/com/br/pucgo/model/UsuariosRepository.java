package com.br.pucgo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{

    List<Usuarios> findAll();
    Optional<Usuarios> findById(Long id);
}
