package com.exemplo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDTO {
    private Long id;
    private String descricao;
    private Long clienteId;
    private List<Long> produtoIds;
    private String status;
}
