package com.exemplo.controller;

import com.exemplo.dto.PedidoDTO;
import com.exemplo.service.PedidoService;
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
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "APIs de gerenciamento de pedidos")
public class PedidoController {

    private final PedidoService service;

    /**
     * Cria um novo pedido
     */
    @PostMapping
    @Operation(summary = "Criar novo pedido", description = "Cria um novo pedido no sistema validando o cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PedidoDTO> criar(@Valid @RequestBody PedidoDTO pedidoDTO) {
        log.info("Recebida requisição para criar pedido para cliente ID: {}", pedidoDTO.getClienteId());
        PedidoDTO pedidoCriado = service.criar(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }

    /**
     * Lista todos os pedidos
     */
    @GetMapping
    @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista de todos os pedidos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class)))
    public ResponseEntity<List<PedidoDTO>> listar() {
        log.info("Recebida requisição para listar pedidos");
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Busca um pedido por ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Retorna os detalhes de um pedido específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
        log.info("Recebida requisição para buscar pedido com ID: {}", id);
        return ResponseEntity.ok(service.buscarPorIdOuLancarException(id));
    }

}