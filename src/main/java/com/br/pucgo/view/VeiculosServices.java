package com.br.pucgo.view;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pucgo.model.Veiculos;
import com.br.pucgo.model.VeiculosRepository;

@Service
@Transactional
public class VeiculosServices {

    @Autowired
    private VeiculosRepository veiculosRepository;
    
    public void inserirVeiculo(Veiculos veiculos){
        veiculosRepository.save(veiculos);
    }

    public void deletarVeiculo(Long id_veiculos){
        veiculosRepository.deleteById(id_veiculos);
    }

    public List<Veiculos> buscarTodosVeiculos(){
        return veiculosRepository.findAll();
    }

    public Optional<Veiculos> buscarVeiculosPorId(Long id_veiculos){
        return veiculosRepository.findById(id_veiculos);
    }
    
}
