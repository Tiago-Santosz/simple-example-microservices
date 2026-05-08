package com.exemplo.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para acesso a dados de auditoria
 */
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}

