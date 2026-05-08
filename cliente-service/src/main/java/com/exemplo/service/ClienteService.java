package com.exemplo.service;

import com.exemplo.dto.ClienteDTO;
import com.exemplo.entity.Cliente;
import com.exemplo.exception.BusinessException;
import com.exemplo.exception.ResourceNotFoundException;
import com.exemplo.mapper.ClienteMapper;
import com.exemplo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper clienteMapper;

    /**
     * Salva um novo cliente
     * @param dto DTO com dados do cliente
     * @return Cliente salvo
     */
    public ClienteDTO salvar(ClienteDTO dto) {
        log.debug("Salvando novo cliente: {}", dto.getNome());

        // Validar se email já existe
        if (repository.existsByEmail(dto.getEmail())) {
            log.warn("Email já cadastrado: {}", dto.getEmail());
            throw new BusinessException("Email já cadastrado no sistema");
        }

        Cliente cliente = repository.save(clienteMapper.toEntity(dto));
        log.info("Cliente salvo com sucesso. ID: {}", cliente.getId());
        return clienteMapper.toDto(cliente);
    }

    /**
     * Busca um cliente por ID
     * @param id ID do cliente
     * @return Optional com o cliente ou vazio
     */
    public Optional<ClienteDTO> buscarPorId(Long id) {
        log.debug("Buscando cliente com ID: {}", id);

        return repository.findById(id)
                .map(c -> {
                    log.debug("Cliente encontrado: {}", c.getNome());
                    return clienteMapper.toDto(c);
                });
    }

    /**
     * Busca um cliente por ID, lança exception se não encontrado
     * @param id ID do cliente
     * @return ClienteDTO
     */
    public ClienteDTO buscarPorIdOuLancarException(Long id) {
        log.debug("Buscando cliente com ID: {}", id);

        return buscarPorId(id)
                .orElseThrow(() -> {
                    log.warn("Cliente não encontrado. ID: {}", id);
                    return new ResourceNotFoundException("Cliente não encontrado com ID: " + id);
                });
    }
}
