package com.exemplo.pedido.service;

import com.exemplo.cliente.dto.ClienteDTO;
import com.exemplo.pedido.dto.PedidoDTO;
import com.exemplo.pedido.entity.Pedido;
import com.exemplo.pedido.repository.PedidoRepository;
import com.exemplo.produto.dto.ProdutoDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final WebClient clienteWebClient;
    private final WebClient produtoWebClient;

    public PedidoService(PedidoRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.clienteWebClient = webClientBuilder.baseUrl("http://localhost:8080").build();
        this.produtoWebClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public Pedido criar(Pedido pedido) {
        clienteWebClient
                .get()
                .uri("/clientes/{id}", pedido.getClienteId())
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block();
        return repository.save(pedido);
    }

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Optional<PedidoDTO> buscarPorIdComProdutos(Long id) {
        return repository
                .findById(id)
                .map(pedido -> {
                    List<ProdutoDTO> produtos = pedido.getProdutoIds().stream()
                            .map(produtoId -> produtoWebClient
                                    .get()
                                    .uri("/produtos/{id}", produtoId)
                                    .retrieve()
                                    .bodyToMono(ProdutoDTO.class)
                                    .block())
                            .collect(Collectors.toList());
                    return new PedidoDTO(pedido, produtos);
                });
    }
}
