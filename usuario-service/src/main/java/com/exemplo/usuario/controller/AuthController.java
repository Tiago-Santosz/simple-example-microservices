package com.exemplo.usuario.controller;

import com.exemplo.usuario.dto.AuthRequest;
import com.exemplo.usuario.dto.AuthResponse;
import com.exemplo.usuario.dto.RegistroRequest;
import com.exemplo.usuario.model.Usuario;
import com.exemplo.usuario.service.JwtService;
import com.exemplo.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegistroRequest request) {
        Usuario usuario = usuarioService.salvar(request);
        String token = jwtService.gerarToken(usuario.getUsername());
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Usuario usuario = usuarioService.autenticar(request);
        String token = jwtService.gerarToken(usuario.getUsername());
        return new AuthResponse(token);
    }
}
