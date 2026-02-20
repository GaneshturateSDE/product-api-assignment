package com.products.products.services;



import com.products.products.dto.ProductRequestDTO;
import com.products.products.entity.Product;
import com.products.products.repository.ItemRepository;
import com.products.products.repository.ProductRepository;
import com.products.products.servicesImpl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository pr;

    @Mock
    private ItemRepository ir;

    @InjectMocks
    private ProductServiceImpl service;

    // ✅ Test Create Product
    @Test
    void testCreateProduct() {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setProductName("Test Product");

        Product savedProduct = new Product();
        savedProduct.setId(1);

        when(pr.save(any(Product.class))).thenReturn(savedProduct);

        var response = service.create(dto);

        assertEquals(200, Integer.valueOf(response.getStatusCode().toString()));
        assertEquals("Product Created", response.getBody().get("message"));
    }

    // ✅ Test Get By Id (Found)
    @Test
    void testGetById_Success() {
        Product product = new Product();
        product.setId(1);
        product.setProductName("Test");

        when(pr.getProductsById(1)).thenReturn(product);

        var response = service.getById(1);

        assertEquals(401, Integer.valueOf(response.getStatusCode().toString())); // ⚠️ your code uses 401
        assertTrue(response.getBody().containsKey("data"));
    }

    // ❌ Test Get By Id (Not Found)
    @Test
    void testGetById_NotFound() {
        when(pr.getProductsById(1)).thenReturn(null);

        var response = service.getById(1);

        assertEquals(401, Integer.valueOf(response.getStatusCode().toString()));
        assertEquals("Product Not Found", response.getBody().get("message"));
    }

    // ✅ Test Get All Products
    @Test
    void testGetAllProducts() {
        Product product = new Product();
        product.setId(1);
        product.setProductName("Test");

        Page<Product> page = new PageImpl<>(java.util.List.of(product));

        when(pr.findAll(any(Pageable.class))).thenReturn(page);

        var response = service.getAll(PageRequest.of(0, 5));

        assertEquals(200, Integer.valueOf(response.getStatusCode().toString()));
        assertTrue(response.getBody().containsKey("data"));
    }

    // ❌ Test Get All (Empty)
    @Test
    void testGetAllProducts_Empty() {
        Page<Product> emptyPage = Page.empty();

        when(pr.findAll(any(Pageable.class))).thenReturn(emptyPage);

        var response = service.getAll(PageRequest.of(0, 5));

        assertEquals(401, Integer.valueOf(response.getStatusCode().toString()));
    }

    // ✅ Test Update Product
    @Test
    void testUpdateProduct() {
        Product existing = new Product();
        existing.setId(1);

        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setProductName("Updated");

        when(pr.getProductsById(1)).thenReturn(existing);

        var response = service.update(1, dto);

        assertEquals(200, Integer.valueOf(response.getStatusCode().toString()));
        assertEquals("Data Updated", response.getBody().get("message"));
    }

    // ❌ Test Update Not Found
    @Test
    void testUpdateProduct_NotFound() {
        when(pr.getProductsById(1)).thenReturn(null);

        var response = service.update(1, new ProductRequestDTO());

        assertEquals("Product Not Found", response.getBody().get("message"));
    }

    // ✅ Test Delete Product
    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setId(1);

        when(pr.getProductsById(1)).thenReturn(product);

        var response = service.delete(1);

        verify(pr, times(1)).deleteById(1);
        assertEquals("Product Deleted", response.getBody().get("message"));
    }

    // ❌ Test Delete Not Found
    @Test
    void testDeleteProduct_NotFound() {
        when(pr.getProductsById(1)).thenReturn(null);

        var response = service.delete(1);

        assertEquals("Product Not Found", response.getBody().get("message"));
    }
}