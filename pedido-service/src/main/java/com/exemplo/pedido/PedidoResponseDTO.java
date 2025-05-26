package com.exemplo.pedido;

import org.example.produto.ProdutoDTO;

import java.util.List;

public class PedidoResponseDTO {
    private Long id;
    private String descricao;
    private Long clienteId;
    private List<ProdutoDTO> produtos;

    public PedidoResponseDTO(Long id, String descricao, Long clienteId, List<ProdutoDTO> produtos) {
        this.id = id;
        this.descricao = descricao;
        this.clienteId = clienteId;
        this.produtos = produtos;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public Long getClienteId() { return clienteId; }
    public List<ProdutoDTO> getProdutos() { return produtos; }
}
