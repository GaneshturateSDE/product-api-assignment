package com.products.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotBlank
    String productName;
}
