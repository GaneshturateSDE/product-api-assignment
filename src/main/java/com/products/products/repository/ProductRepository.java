package com.products.products.repository;

import com.products.products.dto.ProductResponseDTO;
import com.products.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
   Product getProductsById(Integer id);
}
