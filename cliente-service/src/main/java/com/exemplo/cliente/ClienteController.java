package com.exemplo.cliente;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @GetMapping("/{id}")
    public ClienteDTO getCliente(@PathVariable Long id) {
        return new ClienteDTO(id, "Cliente " + id, "cliente" + id + "@email.com");
    }
}