package com.example.ordersservice.infraestructure.mapper;

import com.example.ordersservice.domain.dto.ClienteDTO;
import com.example.ordersservice.infrastructure.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO toDto(Cliente cliente);

    Cliente toEntity(ClienteDTO clienteDTO);
}