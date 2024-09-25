package com.training.task.service;

import java.util.List;

import com.training.task.dto.ProductDTO;

public interface IProductService {

    ProductDTO addProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    
    List<ProductDTO> getAllProducts();

	ProductDTO getProductById(Long id);  
}
