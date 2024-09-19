package com.example.ordersservice.infraestructure.mapper;

import com.example.ordersservice.domain.dto.DetallePedidoDTO;
import com.example.ordersservice.infrastructure.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetallePedidoMapper {

    DetallePedidoMapper INSTANCE = Mappers.getMapper(DetallePedidoMapper.class);

    DetallePedidoDTO toDto(Product product);

    Product toEntity(DetallePedidoDTO detallePedidoDTO);
}

