package com.example.ordersservice.controller;

import com.example.ordersservice.domain.service.OrdersService;
import com.example.ordersservice.exception.OrdersException;
import com.example.ordersservice.infraestructure.entity.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService orderService;

    // POST /orders - Crear un nuevo pedido
    @PostMapping
    public Orders crearPedido(@RequestBody Orders order) {
        try {
            return orderService.crearPedido(order);
        } catch (OrdersException e) {
            // Aquí puedes manejar mejor el error y devolver una respuesta personalizada
            return null;
        }
    }

    // GET /orders/{id} - Obtener detalles de un pedido
    @GetMapping("/{id}")
    public Orders obtenerPedido(@PathVariable Long id) {
        return orderService.obtenerPedido(id);
    }

    // GET /orders - Listar todos los pedidos (opcionalmente por customerId y status)
    @GetMapping
    public List<Orders> listarPedidos(@RequestParam(required = false) Long customerId,
                                     @RequestParam(required = false) String status) {
        return orderService.listarPedidos(customerId, status);
    }

    // PUT /orders/{id}/status - Actualizar el estado de un pedido
    @PutMapping("/{id}/status")
    public String actualizarEstadoPedido(@PathVariable Long id, @RequestBody String status) {
        Orders order = orderService.actualizarEstadoPedido(id, status);
        if (order != null) {
            return "Estado actualizado";
        }
        return "Pedido no encontrado";
    }

    // DELETE /orders/{id} - Eliminar un pedido
    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        orderService.eliminarPedido(id);
        return "Pedido eliminado";
    }
}