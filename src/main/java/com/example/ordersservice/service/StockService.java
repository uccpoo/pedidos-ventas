package com.example.ordersservice.service;

import org.springframework.stereotype.Service;

@Service
public class StockService {

    public boolean validarStock(Long productId, int quantity) {
        // Aquí podrías implementar la lógica para consultar la cantidad en inventario.
        // Por ahora vamos a asumir que hay stock suficiente si la cantidad es <= 10.
        return quantity <= 10;
    }
}
