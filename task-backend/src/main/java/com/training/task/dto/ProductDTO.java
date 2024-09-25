package com.training.task.dto;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    private Long productId;
    
    @NotBlank(message="Product name should not be empty.")
    @Size(min = 1, message = "Product name should not be empty.")
    private String name;
    
    @NotBlank(message="Product description should not be empty.")
    @Size(min = 1, message = "Product name should not be empty.")
    private String description;
    
    @NumberFormat()
    @NotNull(message="Price should not be empty.")
    private Double price;

   
    public Long getProductId() {
        return productId;
    }

    
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
