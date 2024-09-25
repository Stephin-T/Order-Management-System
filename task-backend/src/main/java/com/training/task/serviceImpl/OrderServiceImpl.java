package com.training.task.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.training.task.dto.OrderDTO;
import com.training.task.dto.OrderProductDTO;
import com.training.task.dto.ProductDTO;
import com.training.task.entity.Customer;
import com.training.task.entity.Order;
import com.training.task.entity.OrderProduct;
import com.training.task.entity.Product;
import com.training.task.exception.ResourceNotFoundException;
import com.training.task.mapper.Mapper;
import com.training.task.repo.CustomerRepository;
import com.training.task.repo.OrderRepository;
import com.training.task.repo.ProductRepository;
import com.training.task.service.IOrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final Mapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository, Mapper mapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
       
        if (orderDTO.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer information must be provided");
        }

        Optional<Customer> customerOptional = customerRepository.findById(orderDTO.getCustomerId());
        if (!customerOptional.isPresent()) {
            throw new ResourceNotFoundException("Customer not found with id " + orderDTO.getCustomerId());
        }

        Order order = mapper.toOrder(orderDTO);
        order.setCustomer(customerOptional.get());

        
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDTO orderProductDTO : orderDTO.getOrderProducts()) {
            if (orderProductDTO.getProductId() == null) {
                throw new IllegalArgumentException("Product information must be provided");
            }

            Optional<Product> productOptional = productRepository.findById(orderProductDTO.getProductId());
            if (!productOptional.isPresent()) {
                throw new ResourceNotFoundException("Product not found with id " + orderProductDTO.getProductId());
            }

            OrderProduct orderProduct = mapper.toOrderProduct(orderProductDTO);
            orderProduct.setProduct(productOptional.get());
            orderProduct.setOrder(order);
            orderProducts.add(orderProduct);
        }
        order.setOrderProducts(orderProducts);

        
        Order savedOrder = orderRepository.save(order);
        return mapper.toOrderDTO(savedOrder);
    }

    @Override
    public List<ProductDTO> getProductsByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> order.getOrderProducts().stream()
                        .map(orderProduct -> mapper.toProductDTO(orderProduct.getProduct()))
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ResourceNotFoundException("No orders found with id " + orderId));
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_CustomerId(customerId).stream()
                .map(order -> mapper.toOrderDTO(order)) // Lambda expression
                .collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public void updateOrderProductQuantity(Long orderId, Long productId, int newQuantity) {
        
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));

        
        Optional<OrderProduct> orderProductOptional = order.getOrderProducts().stream()
                .filter(op -> op.getProduct().getProductId().equals(productId))
                .findFirst();

        if (!orderProductOptional.isPresent()) {
            throw new ResourceNotFoundException("Product not found in the order with id " + productId);
        }

        
        OrderProduct orderProduct = orderProductOptional.get();
        orderProduct.setQuantity(newQuantity);

       
        orderRepository.save(order);
    }
}
