package com.exemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PedidoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoServiceApplication.class, args);
    }
}