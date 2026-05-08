package com.exemplo.service;

import com.exemplo.dto.PedidoDTO;
import com.exemplo.entity.Pedido;
import com.exemplo.exception.BusinessException;
import com.exemplo.exception.ResourceNotFoundException;
import com.exemplo.mapper.PedidoMapper;
import com.exemplo.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final RestTemplate restTemplate;
    private final PedidoMapper pedidoMapper;

    /**
     * Cria um novo pedido validando se o cliente existe
     * Faz uma chamada REST ao cliente-service para validar
     */
    public PedidoDTO criar(PedidoDTO dto) {
        log.debug("Criando novo pedido para cliente ID: {}", dto.getClienteId());

        // Validar se cliente existe chamando cliente-service
        validarClienteExiste(dto.getClienteId());

        Pedido pedido = repository.save(pedidoMapper.toEntity(dto));
        log.info("Pedido criado com sucesso. ID: {}", pedido.getId());

        return converterParaDTO(pedido);
    }

    /**
     * Lista todos os pedidos
     */
    public List<PedidoDTO> listar() {
        log.debug("Listando todos os pedidos");
        return repository.findAll()
                .stream()
                .map(pedidoMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Busca um pedido por ID
     */
    public Optional<PedidoDTO> buscarPorId(Long id) {
        log.debug("Buscando pedido com ID: {}", id);
        return repository.findById(id)
                .map(pedido -> {
                    log.debug("Pedido encontrado: {}", pedido.getDescricao());
                    return pedidoMapper.toDto(pedido);
                });
    }

    /**
     * Busca um pedido por ID, lança exception se não encontrado
     */
    public PedidoDTO buscarPorIdOuLancarException(Long id) {
        log.debug("Buscando pedido com ID: {}", id);
        return buscarPorId(id)
                .orElseThrow(() -> {
                    log.warn("Pedido não encontrado. ID: {}", id);
                    return new ResourceNotFoundException("Pedido não encontrado com ID: " + id);
                });
    }

    /**
     * Valida se um cliente existe chamando o cliente-service
     */
    private void validarClienteExiste(Long clienteId) {
        String url = "http://localhost:8080/clientes/" + clienteId;
        try {
            log.debug("Validando cliente ID {} em: {}", clienteId, url);
            Object cliente = restTemplate.getForObject(url, Object.class);
            if (cliente == null) {
                log.warn("Cliente não encontrado. ID: {}", clienteId);
                throw new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId);
            }
            log.debug("Cliente validado com sucesso: {}", clienteId);
        } catch (RestClientException ex) {
            log.error("Erro ao validar cliente no cliente-service", ex);
            throw new BusinessException("Erro ao validar cliente. Cliente-service pode estar indisponível");
        }
    }

    private PedidoDTO converterParaDTO(Pedido pedido) {
        return pedidoMapper.toDto(pedido);
    }
}

