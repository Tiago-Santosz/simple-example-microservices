package com.exemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.exemplo.pedido")
@EntityScan(basePackages = "com.exemplo.pedido")
public class PedidoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoServiceApplication.class, args);
    }
}