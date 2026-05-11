package com.exemplo.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
            "com.exemplo.usuario.controller",
            "com.exemplo.usuario.service",
            "com.exemplo.usuario.security"
        })
@EnableJpaRepositories(basePackages = "com.exemplo.usuario.repository")
@EntityScan(basePackages = "com.exemplo.usuario.entity")
public class UsuarioServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsuarioServiceApplication.class, args);
    }
}
