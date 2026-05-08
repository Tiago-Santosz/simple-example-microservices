package com.exemplo.service;

import com.exemplo.dto.ProdutoDTO;
import com.exemplo.entity.Produto;
import com.exemplo.exception.ResourceNotFoundException;
import com.exemplo.mapper.ProdutoMapper;
import com.exemplo.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper produtoMapper;

    public ProdutoDTO salvar(ProdutoDTO dto) {
        log.debug("Salvando produto {}", dto.getNome());
        Produto produto = repository.save(produtoMapper.toEntity(dto));
        return produtoMapper.toDto(produto);
    }

    public List<ProdutoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(produtoMapper::toDto)
                .toList();
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        return produtoMapper.toDto(produto);
    }

    public Produto buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
    }
}
