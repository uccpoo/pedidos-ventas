package com.example.ordersservice.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ordersservice.infraestructure.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOrderId(Long orderId);
    List<Product> findByPriceBetween(Float minPrice, Float maxPrice);
}
