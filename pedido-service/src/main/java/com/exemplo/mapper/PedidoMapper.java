package com.exemplo.mapper;

import com.exemplo.dto.PedidoDTO;
import com.exemplo.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) {
            return null;
        }
        return Pedido.builder()
                .descricao(dto.getDescricao())
                .clienteId(dto.getClienteId())
                .produtoIds(dto.getProdutoIds())
                .status("PENDENTE")
                .build();
    }

    public PedidoDTO toDto(Pedido entity) {
        if (entity == null) {
            return null;
        }
        return PedidoDTO.builder()
                .descricao(entity.getDescricao())
                .clienteId(entity.getClienteId())
                .produtoIds(entity.getProdutoIds())
                .build();
    }
}
