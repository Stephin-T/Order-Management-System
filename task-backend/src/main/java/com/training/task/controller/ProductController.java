package com.training.task.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.training.task.dto.ProductDTO;
import com.training.task.exception.ErrorResponse;
import com.training.task.exception.ResourceNotFoundException;
import com.training.task.service.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    
    
     Logger logger = LoggerFactory.getLogger(ProductController.class);
    
   
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDTO) {
    	   
    	    	  
        ProductDTO newProduct = productService.addProduct(productDTO);
        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResourceNotFoundException e) {

            ErrorResponse errorResponse = new ErrorResponse("Product not found with id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
    	
    	logger.trace("Viewed all Products"); 
    	
    	
    	
        List<ProductDTO> products = productService.getAllProducts();
        
        
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 
}
