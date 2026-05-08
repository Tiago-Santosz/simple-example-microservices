package com.exemplo.mapper;

import com.exemplo.dto.ProdutoDTO;
import com.exemplo.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }
        return Produto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .preco(dto.getPreco())
                .build();
    }

    public ProdutoDTO toDto(Produto produto) {
        if (produto == null) {
            return null;
        }
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .preco(produto.getPreco())
                .build();
    }
}
