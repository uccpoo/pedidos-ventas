package com.example.ordersservice.infraestructure.mapper;

import com.example.ordersservice.domain.dto.PedidoDTO;
import com.example.ordersservice.infrastructure.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mappings({
        @Mapping(source = "cliente.clienteId", target = "clienteId"),
        @Mapping(source = "productos", target = "detalles")
    })
    PedidoDTO toDto(Order order);

    @Mappings({
        @Mapping(source = "clienteId", target = "cliente.clienteId"),
        @Mapping(source = "detalles", target = "productos")
    })
    Order toEntity(PedidoDTO pedidoDTO);
}
