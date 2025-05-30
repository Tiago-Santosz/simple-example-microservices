package com.exemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.exemplo.cliente")
@EntityScan(basePackages = "com.exemplo.cliente")
public class ClienteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClienteServiceApplication.class, args);
    }
}