package com.br.pucgo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.br.pucgo.view.RelatorioService;
import com.br.pucgo.view.UsuariosServices;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class MycontrollerUsuarios {

    @Autowired
    UsuariosServices usuariosServices;

    @Autowired
    private RelatorioService relatorioService;

    @PostMapping("/inserir")
    public ResponseEntity<String> inserirUsuarios(@RequestBody Usuarios nome_usuarios){
        try {
            usuariosServices.inserirUsuario(nome_usuarios);
            return ResponseEntity.ok("Usuario inserido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir usuario no banco de dados.");
        }
    }

    @PostMapping("/login")
        public ResponseEntity<String> loginUsuario(@RequestBody Usuarios usuario) {
        String nomeUsuario = usuario.getNome_usuario();
        Optional<Usuarios> usuarioEncontrado = usuariosServices.buscarUsuarioPorNome(nomeUsuario);
        
        if (usuarioEncontrado.isPresent()) {
            Usuarios usuarioBanco = usuarioEncontrado.get();
            String senhaCriptografada = usuarioBanco.getSenha_usuario();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean senhaCorreta = encoder.matches(usuario.getSenha_usuario(), senhaCriptografada);
            
            if (senhaCorreta) {
                return ResponseEntity.ok("Login bem-sucedido!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
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

    @GetMapping(value = "/relatorio", produces = MediaType.APPLICATION_PDF_VALUE)
    public void gerarRelatorioUsuarios(HttpServletResponse response) throws IOException {
        try {
            List<Usuarios> usuarios  = new ArrayList<>();

            byte[] relatorio = relatorioService.gerarRelatorioUsuarios(usuarios);

            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename=VehiclesProfits.pdf");

            response.getOutputStream().write(relatorio);
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write("Erro ao gerar o relatório de usuários.");
        }
    }

}
