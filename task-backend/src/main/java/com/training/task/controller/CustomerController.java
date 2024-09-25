package com.training.task.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.training.task.dto.CustomerDTO;
import com.training.task.dto.CustomerDetailsDTO;
import com.training.task.dto.OrderDTO;
import com.training.task.dto.UserDTO;
import com.training.task.service.ICustomerService;
import com.training.task.service.IOrderService;
import com.training.task.service.IUserService;
import com.training.task.serviceImpl.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService customerService;
    private final IOrderService orderService;
    private final IUserService userService; 

    public CustomerController(ICustomerService customerService, IOrderService orderService, IUserService userService) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.userService = userService;
    }
    
    Logger logger = LoggerFactory.getLogger(ProductController.class);
   
    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO newCustomer = customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }
   
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
    	
    	long s = System.currentTimeMillis();
    	logger.info("Request made at  : "+ s);
    	
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        
        logger.info("Response : "+ (System.currentTimeMillis() -s + " milliseconds"));
        return ResponseEntity.ok(customerDTO);
    }
   
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerById(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO updatedCustomerDTO = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(updatedCustomerDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
     
    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId); 
        if (orders != null && !orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/details")
    public ResponseEntity<List<CustomerDetailsDTO>> getAllCustomerDetails() {
        List<CustomerDetailsDTO> customerDetails = customerService.getAllCustomerDetails();
        return ResponseEntity.ok(customerDetails);
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<Boolean> registerNewCustomer(@RequestBody @Valid UserDTO user) {
        Boolean isRegistered = userService.registerNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(isRegistered);
    }

}
