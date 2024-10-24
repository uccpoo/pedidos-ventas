package com.example.ordersservice.infraestructure.mappers;

import com.example.ordersservice.domain.dto.OrdersDetailDTO;
import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class OrdersDetailMapper {

    // Convertir de entidad a DTO
    public OrdersDetailDTO toDTO(OrdersDetail detail) {
        if (detail == null) {
            return null;
        }

        OrdersDetailDTO dto = new OrdersDetailDTO();
        dto.setDetalleId(detail.getDetailId().intValue());
        dto.setPedidoId(detail.getOrder().getOrderId().intValue());
        dto.setProductoId(detail.getProduct().getProductId().intValue());
        dto.setCantidad(detail.getQuantity());
        dto.setPrecio(detail.getPrice());

        return dto;
    }

    // Convertir de DTO a entidad
    public OrdersDetail toEntity(OrdersDetailDTO dto) {
        if (dto == null) {
            return null;
        }

        OrdersDetail detail = new OrdersDetail();
        detail.setDetailId((long) dto.getDetalleId());
        detail.setQuantity(dto.getCantidad());
        detail.setPrice(dto.getPrecio());

        return detail;
    }

    // Método que acepta Order y Product para convertir DTO a entidad
    public OrdersDetail toEntity(OrdersDetailDTO dto, Orders order, Product product) {
        if (dto == null || order == null || product == null) {
            return null;
        }

        OrdersDetail detail = new OrdersDetail();
        detail.setDetailId((long) dto.getDetalleId());
        detail.setQuantity(dto.getCantidad());
        detail.setPrice(dto.getPrecio());
        detail.setOrder(order);
        detail.setProduct(product);

        return detail;
    }
}
