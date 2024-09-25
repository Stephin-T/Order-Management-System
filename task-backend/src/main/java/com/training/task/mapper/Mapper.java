package com.training.task.mapper;

import com.training.task.dto.CustomerDTO;
import com.training.task.dto.OrderDTO;
import com.training.task.dto.OrderProductDTO;
import com.training.task.dto.ProductDTO;
import com.training.task.dto.UserDTO;
import com.training.task.entity.AppUsers;
import com.training.task.entity.Customer;
import com.training.task.entity.Order;
import com.training.task.entity.OrderProduct;
import com.training.task.entity.Product;
import com.training.task.repo.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Mapper {
	
	
	private final ProductRepository productRepository;
	
	
	

    public Mapper(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}


	public CustomerDTO toCustomerDTO(Customer customer) {
        return Optional.ofNullable(customer).map(cust -> {
            CustomerDTO dto = new CustomerDTO();
            dto.setCustomerId(cust.getCustomerId());
            dto.setFirstName(cust.getFirstName());
            dto.setMiddleName(cust.getMiddleName());
            dto.setLastName(cust.getLastName());
            dto.setEmail(cust.getEmail());
            dto.setCompany(cust.getCompany());
            dto.setBillingStreet(cust.getBillingStreet());
            dto.setBillingCity(cust.getBillingCity());
            dto.setBillingState(cust.getBillingState());
            dto.setBillingZip(cust.getBillingZip());
            dto.setOrders(cust.getOrders() != null ? cust.getOrders().stream()
                .map(this::toOrderDTO)
                .collect(Collectors.toList()) : List.of());
            return dto;
        }).orElse(new CustomerDTO());
    }

  
    public OrderDTO toOrderDTO(Order order) {
        return Optional.ofNullable(order).map(ord -> {
            OrderDTO dto = new OrderDTO();
            dto.setOrderId(ord.getOrderId());
            dto.setCustomerId(ord.getCustomer() != null ? ord.getCustomer().getCustomerId() : null);
            dto.setInvoiceCreationDate(ord.getInvoiceCreationDate());
            dto.setDeliveryDueDate(ord.getDeliveryDueDate());
            dto.setPaymentDueDate(ord.getPaymentDueDate());
            dto.setCustomMessage(ord.getCustomMessage());
            dto.setOrderProducts(ord.getOrderProducts() != null ? ord.getOrderProducts().stream()
                .map(this::toOrderProductDTO)
                .collect(Collectors.toList()) : List.of());
            return dto;
        }).orElse(new OrderDTO());
    }

    
    public OrderProductDTO toOrderProductDTO(OrderProduct orderProduct) {
        return Optional.ofNullable(orderProduct).map(op -> {
            OrderProductDTO dto = new OrderProductDTO();
            dto.setOrderProductId(op.getOrderProductId());
            dto.setOrderId(op.getOrder() != null ? op.getOrder().getOrderId() : null);
            dto.setProductId(op.getProduct() != null ? op.getProduct().getProductId() : null);
            Long productId = dto.getProductId();
            Optional<Product> productOptional = productRepository.findById(productId);
            Product product = productOptional.orElse(null);  
            System.out.println(product.toString());
            dto.setQuantity(op.getQuantity());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            return dto;
        }).orElse(new OrderProductDTO());
    }

    
    public ProductDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }


    
    public Customer toCustomer(CustomerDTO customerDTO) {
        return Optional.ofNullable(customerDTO).map(dto -> {
            Customer customer = new Customer();
            customer.setCustomerId(dto.getCustomerId());
            customer.setFirstName(dto.getFirstName());
            customer.setMiddleName(dto.getMiddleName());
            customer.setLastName(dto.getLastName());
            customer.setEmail(dto.getEmail());
            customer.setCompany(dto.getCompany());
            customer.setBillingStreet(dto.getBillingStreet());
            customer.setBillingCity(dto.getBillingCity());
            customer.setBillingState(dto.getBillingState());
            customer.setBillingZip(dto.getBillingZip());
           
            return customer;
        }).orElse(null);
    }

    public Order toOrder(OrderDTO orderDTO) {
        return Optional.ofNullable(orderDTO).map(dto -> {
            Order order = new Order();
            order.setOrderId(dto.getOrderId());
            order.setInvoiceCreationDate(dto.getInvoiceCreationDate());
            order.setDeliveryDueDate(dto.getDeliveryDueDate());
            order.setPaymentDueDate(dto.getPaymentDueDate());
            order.setCustomMessage(dto.getCustomMessage());
            
            return order;
        }).orElse(null);
    }

    public OrderProduct toOrderProduct(OrderProductDTO orderProductDTO) {
        return Optional.ofNullable(orderProductDTO).map(dto -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderProductId(dto.getOrderProductId());
            orderProduct.setQuantity(dto.getQuantity());
            
            return orderProduct;
        }).orElse(null);
    }

    public Product toProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }
    
}
