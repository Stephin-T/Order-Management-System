package com.training.task.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.training.task.dto.ProductDTO;
import com.training.task.entity.Product;
import com.training.task.exception.ResourceNotFoundException;
import com.training.task.mapper.Mapper;
import com.training.task.repo.ProductRepository;
import com.training.task.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = mapper.toProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return mapper.toProductDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        return mapper.toProductDTO(updatedProduct);
    }
    
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapper.toProductDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            
            return mapper.toProductDTO(productOptional.get());
        } else {
            return null;
        }
    }

}

