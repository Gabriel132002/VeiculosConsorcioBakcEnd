package com.br.pucgo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsorcioRepository extends JpaRepository<Consorcio, Long>{

    List<Consorcio> findAll();
    Optional<Consorcio> findById(Long id);    
}
