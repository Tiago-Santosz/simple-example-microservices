package com.exemplo.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        // chama cliente-service
        String url = "http://localhost:8080/clientes/" + pedido.getClienteId();
        Object cliente = restTemplate.getForObject(url, Object.class);
        System.out.println("Cliente encontrado: " + cliente);
        return repository.save(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }
}