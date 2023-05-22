package com.br.pucgo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelasRepository extends JpaRepository<Parcelas, Long>{

    List<Parcelas> findAll();
    Optional<Parcelas> findAById(Long id_Parcelas);
}
