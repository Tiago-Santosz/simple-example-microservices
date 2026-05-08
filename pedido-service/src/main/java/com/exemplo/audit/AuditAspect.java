package com.exemplo.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;

    @Around("execution(* com.exemplo.controller..*(..))")
    public Object auditarRequisicao(ProceedingJoinPoint joinPoint) throws Throwable {
        long inicio = System.currentTimeMillis();
        Object resultado = null;
        int statusCode = 500;
        String descricao = "Sucesso";

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        String endpoint = "N/A";
        String metodo = "N/A";

        try {
            if (attributes != null) {
                request = attributes.getRequest();
                endpoint = request.getRequestURI();
                metodo = request.getMethod();
            }

            resultado = joinPoint.proceed();
            statusCode = extractStatusCode(resultado);

        } catch (Exception ex) {
            descricao = "Erro: " + ex.getMessage();
            statusCode = 400;
            throw ex;

        } finally {
            long fim = System.currentTimeMillis();
            long tempoExecucao = fim - inicio;

            try {
                AuditLog auditLog = AuditLog.builder()
                        .timestamp(java.time.LocalDateTime.now())
                        .usuario("SYSTEM")
                        .acao(joinPoint.getSignature().getName())
                        .endpoint(endpoint)
                        .metodo(metodo)
                        .statusCode(statusCode)
                        .descricao(descricao)
                        .tempoExecucao(tempoExecucao)
                        .build();

                auditLogRepository.save(auditLog);

                log.debug("Requisição auditada: {} {} - Status: {} - Tempo: {}ms",
                        metodo, endpoint, statusCode, tempoExecucao);

            } catch (Exception ex) {
                log.error("Erro ao registrar auditoria", ex);
            }
        }

        return resultado;
    }

    private int extractStatusCode(Object resultado) {
        if (resultado instanceof ResponseEntity<?> response) {
            return response.getStatusCode().value();
        }
        return 200;
    }
}

