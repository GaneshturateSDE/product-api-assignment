package com.products.products.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    private String createdBy;
    private LocalDateTime createdOn;

    private String modifiedBy;
    private LocalDateTime modifiedOn;
}