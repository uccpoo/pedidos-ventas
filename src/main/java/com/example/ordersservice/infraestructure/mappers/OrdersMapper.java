package com.example.ordersservice.infraestructure.mappers;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.infraestructure.entity.Orders;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class OrdersMapper {

    private final OrdersDetailMapper orderDetailMapper;

    // Constructor para inyectar el OrderDetailMapper
    public OrdersMapper(OrdersDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    // Convertir de entidad a DTO
    public OrdersDTO toDTO(Orders order) {
        if (order == null) {
            return null;
        }

        OrdersDTO dto = new OrdersDTO();
        dto.setPedidoId(order.getOrderId().intValue());
        dto.setClienteId(order.getCustomerId().intValue());
        dto.setFecha(order.getFecha());
        dto.setEstado(order.getStatus());
        dto.setTotal(order.getTotalAmount());

        // Mapeo de detalles de la orden usando OrderDetailMapper
        dto.setDetalles(order.getOrderDetails().stream()
            .map(orderDetailMapper::toDTO)
            .collect(Collectors.toList()));

        return dto;
    }

    // Convertir de DTO a entidad
    public Orders toEntity(OrdersDTO dto) {
        if (dto == null) {
            return null;
        }

        Orders order = new Orders();
        order.setOrderId((long) dto.getPedidoId());
        order.setCustomerId((long) dto.getClienteId());
        order.setFecha(dto.getFecha());
        order.setStatus(dto.getEstado());
        order.setTotalAmount(dto.getTotal());

        // Convertir cada detalle de la orden de DTO a entidad
        order.setOrderDetails(dto.getDetalles().stream()
            .map(orderDetailMapper::toEntity)
            .collect(Collectors.toList()));

        return order;
    }
}
