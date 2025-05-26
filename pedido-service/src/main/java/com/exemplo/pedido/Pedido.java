package com.exemplo.pedido;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    // Substitui a associação com a entidade Cliente
    @Column(name = "cliente_id")
    private Long clienteId;

    // Aqui você pode manter a associação com Produto
    @ElementCollection
    @CollectionTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "produto_id")
    private List<Long> produtoIds;

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<Long> getProdutoIds() { return produtoIds; }
    public void setProdutoIds(List<Long> produtoIds) { this.produtoIds = produtoIds; }
}
