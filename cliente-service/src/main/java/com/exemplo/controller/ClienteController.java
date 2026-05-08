package com.exemplo.controller;

import com.exemplo.dto.ClienteDTO;
import com.exemplo.service.ClienteService;
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

@Slf4j
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "APIs de gerenciamento de clientes")
public class ClienteController {

    private final ClienteService service;

    /**
     * Salva um novo cliente
     */
    @PostMapping
    @Operation(summary = "Criar novo cliente", description = "Cria um novo cliente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou email já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteDTO dto) {
        log.info("Recebida requisição para criar cliente: {}", dto.getEmail());
        ClienteDTO salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    /**
     * Busca um cliente por ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Retorna os detalhes de um cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
        log.info("Recebida requisição para buscar cliente com ID: {}", id);
        return ResponseEntity.ok(service.buscarPorIdOuLancarException(id));
    }
}
