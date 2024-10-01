package com.example.ordersservice.infraestructure.mapper;

import com.example.ordersservice.domain.dto.ProductoDTO;
import com.example.ordersservice.infrastructure.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    ProductoDTO toDto(Product product);

    Product toEntity(ProductoDTO productoDTO);