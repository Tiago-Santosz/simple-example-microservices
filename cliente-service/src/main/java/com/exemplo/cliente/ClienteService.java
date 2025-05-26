package com.exemplo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail());
        cliente = repository.save(cliente);
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }

    public Optional<ClienteDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getEmail()));
    }
}
