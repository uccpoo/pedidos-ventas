package com.example.ordersservice.domain.service;

import com.example.ordersservice.domain.repository.OrderRepository;
import com.example.ordersservice.exception.OrderException;
import com.example.ordersservice.infraestructure.entity.Order;
import com.example.ordersservice.infraestructure.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockService stockService;

    public Order crearPedido(Order order) throws OrderException {
        for (Product product : order.getProducts()) {
            if (!stockService.validarStock(product.getProductId(), product.getQuantity())) {
                throw new OrderException("Stock insuficiente para el producto con ID: " + product.getProductId());
            }
        }

        double totalAmount = order.getProducts().stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);
        order.setStatus("pendiente");

        return orderRepository.save(order);
    }

    public Order obtenerPedido(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Order> listarPedidos(Long customerId, String status) {
        if (customerId != null) {
            return orderRepository.findByCustomerId(customerId);
        } else if (status != null) {
            return orderRepository.findByStatus(status);
        }
        return (List<Order>) orderRepository.findAll();
    }

    public Order actualizarEstadoPedido(Long orderId, String status) {
        Order order = obtenerPedido(orderId);
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