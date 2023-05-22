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

import com.br.pucgo.model.Consorcio;
import com.br.pucgo.view.ConsorcioService;

@RestController
@RequestMapping("/consorcio")
public class MyControllerConsorcio {

    @Autowired
    ConsorcioService consorcioService;

    @PostMapping
    public ResponseEntity<String> inserirConsorcio(@RequestBody Consorcio consorcio){
        try {
            consorcioService.inserirConsorcio(consorcio);
            return ResponseEntity.ok("Consorcio inserido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir consorcio no banco de dados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consorcio> buscarConsorcioPorId(@PathVariable Long id) {
        Optional<Consorcio> consorcio = consorcioService.buscarConsorcioPorId(id);
        if (consorcio.isPresent()) {
            return ResponseEntity.ok(consorcio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id_consorcio}")
    public ResponseEntity<String> deletarConsorcio(@PathVariable Long id_consorcio) {
        try {
            consorcioService.deletarConsorcio(id_consorcio);
            return ResponseEntity.ok("Consorcio deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar consorcio no banco de dados. Ou usuário já deletado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Consorcio>> buscarTodosConsorcio() {
        List<Consorcio> consorcio = consorcioService.buscarTodosConsorcio();
        return ResponseEntity.ok(consorcio);
    }
    
}
