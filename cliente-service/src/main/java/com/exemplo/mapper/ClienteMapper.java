package com.exemplo.mapper;

import com.exemplo.dto.ClienteDTO;
import com.exemplo.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail());
    }

    public ClienteDTO toDto(Cliente entity) {
        if (entity == null) {
            return null;
        }
        return ClienteDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .build();
    }
}
