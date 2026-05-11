package com.exemplo.produto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.exemplo.produto.controller", "com.exemplo.produto.service"})
@EnableJpaRepositories(basePackages = "com.exemplo.produto.repository")
@EntityScan(basePackages = "com.exemplo.produto.entity")
public class ProdutoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoServiceApplication.class, args);
    }
}
