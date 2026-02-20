package com.products.products.servicesImpl;


import com.products.products.dto.ItemResponseDTO;
import com.products.products.dto.ProductRequestDTO;
import com.products.products.dto.ProductResponseDTO;
import com.products.products.entity.Item;
import com.products.products.entity.Product;
import com.products.products.repository.ItemRepository;
import com.products.products.repository.ProductRepository;
import com.products.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository pr;

    @Autowired
    ItemRepository ir;

    @Override
    public ResponseEntity<Map<String,Object>> create(ProductRequestDTO product) {
         Product prod=new Product();
         prod.setProductName(product.getProductName());
         prod.setCreatedOn(LocalDateTime.now());
         pr.save(prod);
         return ResponseEntity.ok(Map.of("message","Product Created"));
    }

    @Override
    public ResponseEntity<Map<String,Object>> getById(Integer id) {
       Product product=pr.getProductsById(id);
         if(product==null)
             return  ResponseEntity.status(401).body(Map.of("message","Product Not Found"));

        ProductResponseDTO prd=new ProductResponseDTO();
             prd.setId(product.getId());
             prd.setProductName(product.getProductName());


      return ResponseEntity.status(401).body(Map.of("data",prd));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getItemsByProduct(Integer id) {
        Product prd=pr.getProductsById(id);
        ProductResponseDTO p=new ProductResponseDTO();
        p.setId(prd.getId());
        p.setProductName(prd.getProductName());
        List<Item> items=ir.findByProductId(id);
        if(items.isEmpty()) return ResponseEntity.status(404).body(Map.of("message","Items Not Found"));

        List<ItemResponseDTO> itemRes=items.stream().map(it->{
            ItemResponseDTO itm=new ItemResponseDTO();
            itm.setId(it.getId());
            itm.setQuantity(it.getQuantity());
            return itm;
        }).toList();

       return ResponseEntity.status(401).body(Map.of("data",List.of(p,itemRes)));
    }

    @Override
    public ResponseEntity<Map<String,Object>> getAll(Pageable pageable) {
        Page<Product> products=pr.findAll(pageable);
        if(products.isEmpty())
               return ResponseEntity.status(404).body(Map.of("message","No Products"));
        List<ProductResponseDTO> prd=products.stream().map(product -> {
            ProductResponseDTO pr= new ProductResponseDTO();
            pr.setProductName(product.getProductName());
            pr.setId(product.getId());
            return  pr;
        }).toList();

        return ResponseEntity.ok(Map.of("data",prd));
    }

    @Override
    public ResponseEntity<Map<String,Object>> update(Integer id,ProductRequestDTO product) {
         Product prod=pr.getProductsById(id);
          if(prod==null)  return ResponseEntity.ok(Map.of("message","Product Not Found"));
         prod.setProductName(product.getProductName());
         pr.save(prod);

   return ResponseEntity.ok(Map.of("message","Data Updated"));
    }

    @Override
    public ResponseEntity<Map<String,Object>> delete(Integer id) {
        Product prod=pr.getProductsById(id);
        if(prod==null)
            return ResponseEntity.ok(Map.of("message","Product Not Found"));

        pr.deleteById(id);

        return ResponseEntity.ok(Map.of("message","Product Deleted"));
    }
}
