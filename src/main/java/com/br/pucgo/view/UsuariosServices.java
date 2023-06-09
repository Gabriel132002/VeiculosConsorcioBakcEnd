package com.br.pucgo.view;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.br.pucgo.model.Usuarios;
import com.br.pucgo.model.UsuariosRepository;

@Service
@Transactional
public class UsuariosServices {

    @Autowired
    private UsuariosRepository usuariosRepository;
    private final BCryptPasswordEncoder senhaEncoder;

    public UsuariosServices(BCryptPasswordEncoder senhaEncoder) {
        this.senhaEncoder = senhaEncoder;
    }

    public void inserirUsuario(Usuarios usuarios) {
        String senhaCriptografada = senhaEncoder.encode(usuarios.getSenha_usuario());
        usuarios.setSenha_usuario(senhaCriptografada);
        usuariosRepository.save(usuarios);
    }

    public void deletarUsuario(Long id_usuarios) {
        usuariosRepository.deleteById(id_usuarios);
    }

    public List<Usuarios> buscarTodosUsuarios() {
        return usuariosRepository.findAll();
    }

    public Optional<Usuarios> buscarUsuariosPorId(Long id_usuarios) {
        return usuariosRepository.findById(id_usuarios);
    }

    public Optional<Usuarios> buscarUsuarioPorNome(String nome_usuario) {
        return usuariosRepository.findByNomeUsuario(nome_usuario);
    }

    public void atualizarUsuario(Long id_usuarios, Usuarios usuarioAtualizado) {
        Optional<Usuarios> usuarioExistente = usuariosRepository.findById(id_usuarios);
        if (usuarioExistente.isPresent()) {
            Usuarios usuario = usuarioExistente.get();
            usuario.setNome_usuario(usuarioAtualizado.getNome_usuario());
            usuario.setEmail_usuario(usuarioAtualizado.getEmail_usuario());
            usuario.setSenha_usuario(usuarioAtualizado.getSenha_usuario());

            usuariosRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
