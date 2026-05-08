package com.exemplo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;

    private List<Long> produtoIds;
}
