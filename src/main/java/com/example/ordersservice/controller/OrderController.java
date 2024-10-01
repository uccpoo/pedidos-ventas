package com.example.ordersservice.controller;

import com.example.ordersservice.domain.service.OrderService;
import com.example.ordersservice.exception.OrderException;
import com.example.ordersservice.infraestructure.entity.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order crearPedido(@RequestBody Order order) {
        try {
            return orderService.crearPedido(order);
        } catch (OrderException e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Order obtenerPedido(@PathVariable Long id) {
        return orderService.obtenerPedido(id);
    }

    @GetMapping
    public List<Order> listarPedidos(@RequestParam(required = false) Long customerId,
                                     @RequestParam(required = false) String status) {
        return orderService.listarPedidos(customerId, status);
    }

    @PutMapping("/{id}/status")
    public String actualizarEstadoPedido(@PathVariable Long id, @RequestBody String status) {
        Order order = orderService.actualizarEstadoPedido(id, status);
        if (order != null) {
            return "Estado actualizado";
        }
        return "Pedido no encontrado";
    }

    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        orderService.eliminarPedido(id);
        return "Pedido eliminado";
    }
}