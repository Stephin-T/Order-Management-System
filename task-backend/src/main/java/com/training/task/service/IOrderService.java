package com.training.task.service;

import java.util.List;

import com.training.task.dto.OrderDTO;
import com.training.task.dto.ProductDTO;

public interface IOrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    List<ProductDTO> getProductsByOrderId(Long orderId);
    List<OrderDTO> getOrdersByCustomerId(Long customerId);
    void updateOrderProductQuantity(Long orderId, Long productId, int newQuantity);
}
