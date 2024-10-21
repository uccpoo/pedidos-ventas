-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS tabla_pedidosventas;
USE tabla_pedidosventas;

-- Crear tabla Customer
CREATE TABLE IF NOT EXISTS customer (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    direccion VARCHAR(255)
);

-- Crear tabla Product
CREATE TABLE IF NOT EXISTS product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price DOUBLE NOT NULL,
    stock INT NOT NULL CHECK (stock >= 0)
);

-- Crear tabla Orders
CREATE TABLE IF NOT EXISTS orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    total_amount DOUBLE NOT NULL,
    status VARCHAR(50),
    fecha DATE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL
);

-- Crear tabla OrdersDetail
CREATE TABLE IF NOT EXISTS orders_detail (
    detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DOUBLE NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE SET NULL
);
