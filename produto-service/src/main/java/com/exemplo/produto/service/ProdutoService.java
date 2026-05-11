package com.exemplo.produto.service;

import com.exemplo.produto.dto.ProdutoDTO;
import com.exemplo.produto.entity.Produto;
import com.exemplo.produto.repository.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = new Produto(null, dto.getNome(), dto.getPreco());
        produto = repository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    public List<ProdutoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco()))
                .toList();
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id).orElseThrow();
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    public Produto buscarEntidade(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
