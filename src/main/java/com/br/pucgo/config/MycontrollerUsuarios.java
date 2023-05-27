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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pucgo.model.Usuarios;
import com.br.pucgo.view.UsuariosServices;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class MycontrollerUsuarios {

    @Autowired
    UsuariosServices usuariosServices;

    @PostMapping("/inserir")
    public ResponseEntity<String> inserirUsuarios(@RequestBody Usuarios nome_usuarios){
        try {
            usuariosServices.inserirUsuario(nome_usuarios);
            return ResponseEntity.ok("Usuario inserido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir usuario no banco de dados.");
        }
    }

    @GetMapping("/buscar/{id_usuarios}")
    public ResponseEntity<Usuarios> buscarUsuarioPorId(@PathVariable Long id_usuarios) {
        Optional<Usuarios> usuarios = usuariosServices.buscarUsuariosPorId(id_usuarios);
        if (usuarios.isPresent()) {
            return ResponseEntity.ok(usuarios.orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id_usuarios}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id_usuarios) {
        try {
            usuariosServices.deletarUsuario(id_usuarios);
            return ResponseEntity.ok("Usuario deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar usuario no banco de dados. Ou usuário já deletado");
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Usuarios>> buscarTodosUsuarios() {
        List<Usuarios> usuarios = usuariosServices.buscarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/atualizar/{id_usuarios}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id_usuarios, @RequestBody Usuarios usuarioAtualizado) {
        try {
            usuariosServices.atualizarUsuario(id_usuarios, usuarioAtualizado);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário no banco de dados.");
        }
    }

}
