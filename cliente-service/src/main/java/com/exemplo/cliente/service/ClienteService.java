package com.exemplo.cliente.service;

import com.exemplo.cliente.dto.ClienteDTO;
import com.exemplo.cliente.entity.Cliente;
import com.exemplo.cliente.repository.ClienteRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail());
        cliente = repository.save(cliente);
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }

    public Optional<ClienteDTO> buscarPorId(Long id) {
        return repository.findById(id).map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getEmail()));
    }
}
