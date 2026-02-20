-- Create Database
CREATE DATABASE IF NOT EXISTS product_db;
USE product_db;

-- Product Table
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    created_by VARCHAR(100),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(100),
    modified_on TIMESTAMP
);

-- Item Table
CREATE TABLE item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id)
        REFERENCES product(id)
        ON DELETE CASCADE
);

-- User Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50)
);

-- Refresh Token Table
CREATE TABLE refresh_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(500) UNIQUE NOT NULL,
    username VARCHAR(150),
    expiry_date TIMESTAMP
);

-- Sample Data
INSERT INTO product (product_name, created_by)
VALUES 
('iPhone 15', 'admin'),
('Samsung S23', 'admin'),
('MacBook Pro', 'admin');

INSERT INTO item (product_id, quantity)
VALUES 
(1, 10),
(1, 5),
(2, 8),
(3, 12);
