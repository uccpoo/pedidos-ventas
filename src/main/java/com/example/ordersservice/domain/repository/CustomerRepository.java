package com.example.ordersservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ordersservice.infraestructure.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Buscar cliente por correo electrónico
    Customer findByEmail(String email);
}