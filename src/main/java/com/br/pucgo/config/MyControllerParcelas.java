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

import com.br.pucgo.model.Parcelas;
import com.br.pucgo.view.ParcelasService;

@RestController
@RequestMapping("/parcelas")
public class MyControllerParcelas {

    @Autowired
    ParcelasService parcelasService;

    @PostMapping
    public ResponseEntity<String> inserirUsuarios(@RequestBody Parcelas parcelas){
        try {
            parcelasService.inserirParcela(parcelas);
            return ResponseEntity.ok("Parcela inserida com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir parcela no banco de dados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcelas> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Parcelas> parcelas = parcelasService.buscarParcelasPorId(id);
        if (parcelas.isPresent()) {
            return ResponseEntity.ok(parcelas.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id_parcelas}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id_usuarios) {
        try {
            parcelasService.deletarParcela(id_usuarios);
            return ResponseEntity.ok("Parcelas deletada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar parcela no banco de dados. Ou usuário já deletado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Parcelas>> buscarTodosUsuarios() {
        List<Parcelas> parcelas = parcelasService.buscarTodasParcelas();
        return ResponseEntity.ok(parcelas);
    }
}
