package com.exemplo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "ID do cliente é obrigatório")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @ElementCollection
    @CollectionTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "produto_id")
    private List<Long> produtoIds;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'PENDENTE'")
    private String status = "PENDENTE";
}
