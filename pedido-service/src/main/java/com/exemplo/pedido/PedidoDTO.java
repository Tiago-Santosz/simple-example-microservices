package com.exemplo.pedido;

import java.util.List;

public class PedidoDTO {
    private String descricao;
    private Long clienteId;
    private List<Long> produtoIds;


    // Getters e setters
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<Long> getProdutoIds() { return produtoIds; }
    public void setProdutoIds(List<Long> produtoIds) { this.produtoIds = produtoIds; }
}
