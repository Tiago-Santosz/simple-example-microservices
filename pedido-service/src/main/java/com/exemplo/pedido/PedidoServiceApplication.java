package com.exemplo.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {"com.exemplo.pedido.controller", "com.exemplo.pedido.service", "com.exemplo.pedido.config"})
@EnableJpaRepositories(basePackages = "com.exemplo.pedido.repository")
@EntityScan(basePackages = "com.exemplo.pedido.entity")
public class PedidoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidoServiceApplication.class, args);
    }
}
