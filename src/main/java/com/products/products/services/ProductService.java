package com.products.products.services;

import com.products.products.dto.ProductRequestDTO;
import com.products.products.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<Map<String,Object>> create(ProductRequestDTO product);
    ResponseEntity<Map<String,Object>> getById(Integer id);
    ResponseEntity<Map<String,Object>> getItemsByProduct(Integer id);
    ResponseEntity<Map<String,Object>> getAll(Pageable pageable);
    ResponseEntity<Map<String,Object>> update(Integer id,ProductRequestDTO product);
    ResponseEntity<Map<String,Object>> delete(Integer id);
}
