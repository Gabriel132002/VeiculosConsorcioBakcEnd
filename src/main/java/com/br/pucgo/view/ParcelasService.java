package com.br.pucgo.view;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pucgo.model.Parcelas;
import com.br.pucgo.model.ParcelasRepository;


@Service
public class ParcelasService {
    
    @Autowired
    private ParcelasRepository parcelasRepository;

    public void inserirParcela(Parcelas parcelas){
        parcelasRepository.save(parcelas);
    }

    public void deletarParcela(Long id_parcelas){
        parcelasRepository.deleteById(id_parcelas);
    }

    public List<Parcelas> buscarTodasParcelas(){
        return parcelasRepository.findAll();
    }

    public Optional<Parcelas> buscarParcelasPorId(Long id_parcelas){
        return parcelasRepository.findById(id_parcelas);
    }

}
