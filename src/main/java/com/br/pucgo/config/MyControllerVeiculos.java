package com.br.pucgo.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pucgo.model.Veiculos;
import com.br.pucgo.view.VeiculosServices;

@RestController
@RequestMapping("/veiculos")
public class MyControllerVeiculos {
   
    @Autowired
    VeiculosServices veiculosServices;

    //veiculos

    @PostMapping
    public ResponseEntity<String> inserirVeiculo(@RequestBody Veiculos veiculos){
        try {
            veiculosServices.inserirVeiculo(veiculos);
            return ResponseEntity.ok("Veiculo inserido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir veiculo no banco de dados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculos> buscarVeiculoPorId(@PathVariable Long id) {
        Optional<Veiculos> veiculos = veiculosServices.buscarVeiculosPorId(id);
        if (veiculos.isPresent()) {
            return ResponseEntity.ok(veiculos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id_veiculos}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable Long id_veiculos) {
        try {
            veiculosServices.deletarVeiculo(id_veiculos);
            return ResponseEntity.ok("Veiculo deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar veiculo no banco de dados.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Veiculos>> buscarTodosVeiculos() {
        List<Veiculos> veiculos = veiculosServices.buscarTodosVeiculos();
        return ResponseEntity.ok(veiculos);
    }


}
