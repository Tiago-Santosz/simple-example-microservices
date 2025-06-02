package com.exemplo.usuario.service;

import com.exemplo.usuario.dto.AuthRequest;
import com.exemplo.usuario.dto.RegistroRequest;
import com.exemplo.usuario.model.Usuario;
import com.exemplo.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvar(RegistroRequest request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getSenha()));
        return repository.save(usuario);
    }

    public Usuario autenticar(AuthRequest request) {
        return repository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getSenha(), user.getPassword()))
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));
    }
}


