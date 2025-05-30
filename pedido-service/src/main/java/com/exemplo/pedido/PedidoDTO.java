package com.exemplo.pedido;

import org.example.produto.ProdutoDTO;

import java.util.List;

public class PedidoDTO {
    private Long id;
    private String descricao;
    private Long clienteId;
    private List<ProdutoDTO> produtos;

    public PedidoDTO(Pedido pedido, List<ProdutoDTO> produtos) {
        this.id = pedido.getId();
        this.descricao = pedido.getDescricao();
        this.clienteId = pedido.getClienteId();
        this.produtos = produtos;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<ProdutoDTO> getProdutos() { return produtos; }
    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}
