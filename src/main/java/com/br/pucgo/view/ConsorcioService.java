package com.br.pucgo.view;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pucgo.model.Consorcio;
import com.br.pucgo.model.ConsorcioRepository;

@Service
@Transactional
public class ConsorcioService {

    @Autowired
    private ConsorcioRepository consorcioRepository;

    public void inserirConsorcio(Consorcio consorcio){
        consorcioRepository.save(consorcio);
    }

    public void deletarConsorcio(Long consorcio){
        consorcioRepository.deleteById(consorcio);
    }

    public List<Consorcio> buscarTodosConsorcio(){
        return consorcioRepository.findAll(); 
    }

    public Optional<Consorcio> buscarConsorcioPorId(Long id_consorcio){
        return consorcioRepository.findById(id_consorcio);
    }
    
}
