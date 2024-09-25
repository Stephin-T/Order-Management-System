package com.training.task.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.task.dto.CustomerDTO;
import com.training.task.dto.CustomerDetailsDTO;
import com.training.task.dto.OrderDTO;
import com.training.task.exception.ErrorResponse;
import com.training.task.service.ICustomerService;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final ICustomerService customerService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	public UserController(ICustomerService customerService) {
        this.customerService = customerService;
    }
	
	@GetMapping("/currentUser")
    public ResponseEntity<Object> currentUser(Principal principal) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(principal.getName());
    }
	
	@GetMapping("/myDetails")
	public ResponseEntity<Object> getCustomer(Principal principal) {
	    try {
	        String loggedInUser = principal.getName();
	        logger.info("Logged in User: " + loggedInUser);
	        CustomerDetailsDTO customerDTO = customerService.getOnlyCustomerByEmail(loggedInUser);
	        return ResponseEntity.ok(customerDTO);
	    } catch (Exception e) {
	        ErrorResponse errorResponse = new ErrorResponse("Invalid user or password");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	    }
	}

	
	@GetMapping("/myOrders")
    public ResponseEntity<List<OrderDTO>> getOrders(Principal principal) throws Exception {
		
		String loggedInUser = principal.getName();
		logger.info("Logged in User: "+ loggedInUser);
        CustomerDTO customerDTO = customerService.getCustomerByEmail(loggedInUser);
        List<OrderDTO> orders = customerDTO.getOrders();
        return ResponseEntity.ok(orders);
    }

}
