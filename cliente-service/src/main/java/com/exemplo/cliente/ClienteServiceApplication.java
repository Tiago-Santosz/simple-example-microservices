package com.exemplo.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.exemplo.cliente.controller", "com.exemplo.cliente.service"})
@EnableJpaRepositories(basePackages = "com.exemplo.cliente.repository")
@EntityScan(basePackages = "com.exemplo.cliente.entity")
public class ClienteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienteServiceApplication.class, args);
    }
}
