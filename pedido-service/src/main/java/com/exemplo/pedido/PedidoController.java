package com.exemplo.pedido;

import com.exemplo.cliente.ClienteDTO;
import org.example.produto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    private final WebClient clienteWebClient;
    private final WebClient produtoWebClient;

    @Autowired
    public PedidoController(WebClient.Builder webClientBuilder) {
        this.clienteWebClient = webClientBuilder.baseUrl("http://localhost:8080").build();
        this.produtoWebClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        // chama cliente-service usando WebClient
        String url = "/clientes/" + pedido.getClienteId();
        ClienteDTO cliente = clienteWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block(); // Bloqueio apenas para simplificação
        System.out.println("Cliente encontrado: " + cliente);
        return repository.save(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorIdComProdutos(@PathVariable Long id) {
        return repository.findById(id)
                .map(pedido -> {
                    List<ProdutoDTO> produtos = pedido.getProdutoIds().stream()
                            .map(produtoId -> produtoWebClient.get()
                                    .uri("/produtos/{id}", produtoId)
                                    .retrieve()
                                    .bodyToMono(ProdutoDTO.class)
                                    .block()) // Blocking for simplicity
                            .collect(Collectors.toList());

                    PedidoDTO pedidoDTO = new PedidoDTO(pedido, produtos);
                    return ResponseEntity.ok(pedidoDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}