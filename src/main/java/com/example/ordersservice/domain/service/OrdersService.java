package com.example.ordersservice.domain.service;

import com.example.ordersservice.domain.repository.OrdersRepository;
import com.example.ordersservice.exception.OrdersException;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import com.example.ordersservice.infraestructure.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private StockService stockService;

    public Orders crearPedido(Orders order) throws OrdersException {
        // Validación de stock antes de procesar el pedido
        for (OrdersDetail detail : order.getOrderDetails()) {
            Product product = detail.getProduct();
            if (!stockService.validarStock(product.getProductId(), detail.getQuantity())) {
                throw new OrdersException("Stock insuficiente para el producto con ID: " + product.getProductId());
            }
        }

        // Calcular el totalAmount
        double totalAmount = order.getOrderDetails().stream()
                .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);
        order.setStatus("pendiente");

        return orderRepository.save(order);
    }

    public Orders obtenerPedido(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Orders> listarPedidos(Long customerId, String status) {
        if (customerId != null) {
            return orderRepository.findByCustomerId(customerId);
        } else if (status != null) {
            return orderRepository.findByStatus(status);
        }
        return (List<Orders>) orderRepository.findAll();
    }

    public Orders actualizarEstadoPedido(Long orderId, String status) {
        Orders order = obtenerPedido(orderId);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

    public void eliminarPedido(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
