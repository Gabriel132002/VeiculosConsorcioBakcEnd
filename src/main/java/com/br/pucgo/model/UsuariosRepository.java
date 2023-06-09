package com.br.pucgo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{

    @Query("SELECT u FROM Usuarios u WHERE u.nome_usuario = :nomeUsuario")
    Optional<Usuarios> findByNomeUsuario(@Param("nomeUsuario") String nomeUsuario);
    List<Usuarios> findAll();
    Optional<Usuarios> findById(Long id);
}
