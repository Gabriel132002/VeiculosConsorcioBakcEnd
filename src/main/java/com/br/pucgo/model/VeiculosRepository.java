package com.br.pucgo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculosRepository extends JpaRepository<Veiculos, Long>{

    List<Veiculos> findAll();
    Optional<Veiculos> findById(Long id);

}
