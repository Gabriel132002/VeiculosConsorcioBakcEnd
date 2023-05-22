package com.br.pucgo.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pucgo.model.Usuarios;
import com.br.pucgo.view.UsuariosServices;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class MycontrollerUsuarios {

    @Autowired
    UsuariosServices usuariosServices;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<String> inserirUsuarios(@RequestBody Usuarios usuarios){
        try {
            usuariosServices.inserirUsuario(usuarios);
            return ResponseEntity.ok("Usuario inserido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir usuario no banco de dados.");
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuarios> usuarios = usuariosServices.buscarUsuariosPorId(id);
        if (usuarios.isPresent()) {
            return ResponseEntity.ok(usuarios.orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id_usuarios}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id_usuarios) {
        try {
            usuariosServices.deletarUsuario(id_usuarios);
            return ResponseEntity.ok("Usuario deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar usuario no banco de dados. Ou usuário já deletado");
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<Usuarios>> buscarTodosUsuarios() {
        List<Usuarios> usuarios = usuariosServices.buscarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
