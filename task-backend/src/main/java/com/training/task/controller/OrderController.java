package com.training.task.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.training.task.dto.OrderDTO;
import com.training.task.dto.ProductDTO;
import com.training.task.exception.ErrorResponse;
import com.training.task.exception.ResourceNotFoundException;
import com.training.task.service.IOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }
     
    @GetMapping("/{orderId}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByOrderId(@PathVariable Long orderId) {
        List<ProductDTO> products = orderService.getProductsByOrderId(orderId);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        try {
            OrderDTO newOrder = orderService.createOrder(orderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @PutMapping("/{orderId}/products/{productId}/quantity")
    public ResponseEntity<?> updateOrderProductQuantity(
            @PathVariable Long orderId,
            @PathVariable Long productId,
            @RequestParam int quantity) {

        try {
            orderService.updateOrderProductQuantity(orderId, productId, quantity);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse("The specified order or product was not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    @GetMapping("/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
        try {
            List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId);
            return ResponseEntity.ok(orders);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
