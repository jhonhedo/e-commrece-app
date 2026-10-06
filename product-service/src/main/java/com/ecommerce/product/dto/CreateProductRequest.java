package com.ecommerce.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Product Name is Required")
        String name, String description,
        @NotNull(message = "Price is Required")
        @DecimalMin(value = "0.01", message = "price must be greater than Zero")
        BigDecimal price) {

}
