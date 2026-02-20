package com.products.products.controllers;

import com.products.products.dto.ProductRequestDTO;
import com.products.products.entity.Product;
import com.products.products.services.ProductService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<Map<String,Object>> getItemsByProduct(@PathVariable Integer id) {
        return service.getItemsByProduct(id);
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> create(@RequestBody @Validated ProductRequestDTO product) {
        return service.create(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> update(@PathVariable Integer id, @RequestBody ProductRequestDTO product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
