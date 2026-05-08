package com.exemplo.audit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Entidade para registro de auditoria de requisições HTTP
 */
@Entity
@Table(name = "audit_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "usuario", columnDefinition = "VARCHAR(255) DEFAULT 'SYSTEM'")
    private String usuario;

    @Column(nullable = false)
    private String acao;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private String metodo;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "tempo_execucao")
    private Long tempoExecucao;
}

