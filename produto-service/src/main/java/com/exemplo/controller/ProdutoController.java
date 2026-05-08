package com.exemplo.controller;

import com.exemplo.dto.ProdutoDTO;
import com.exemplo.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "APIs de gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Criar novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoDTO produto) {
        log.info("Recebida requisição para criar produto {}", produto.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produto));
    }

    @GetMapping
    @Operation(summary = "Listar produtos")
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }
}
