package org.example.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = new Produto(null, dto.getNome(), dto.getPreco());
        produto = repository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    public List<ProdutoDTO> listarTodos() {
        return repository.findAll()
                .stream()
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
