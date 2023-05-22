package com.br.pucgo.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.br.pucgo.model.Usuarios;
import com.br.pucgo.model.UsuariosRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuariosServices {
    
    @Autowired
    private UsuariosRepository usuariosRepository;
    private final BCryptPasswordEncoder senhaEncoder;
    
    public void inserirUsuario(Usuarios usuarios){
        String senhaCriptografada = senhaEncoder.encode(usuarios.getSenha_usuario());
        usuarios.setSenha_usuario(senhaCriptografada);
        usuariosRepository.save(usuarios);
    }

    public void deletarUsuario(Long id_usuarios){
        usuariosRepository.deleteById(id_usuarios);
    }

    public List<Usuarios> buscarTodosUsuarios(){
        return usuariosRepository.findAll();
    }

    public Optional<Usuarios> buscarUsuariosPorId(Long id_usuarios){
        return usuariosRepository.findById(id_usuarios);
    }

}
