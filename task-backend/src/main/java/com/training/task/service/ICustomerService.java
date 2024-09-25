package com.training.task.service;

import java.util.List;

import com.training.task.dto.CustomerDTO;
import com.training.task.dto.CustomerDetailsDTO;

public interface ICustomerService {
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long customerId);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);
    List<CustomerDetailsDTO> getAllCustomerDetails();
    CustomerDTO getCustomerByEmail(String email) throws Exception;
    CustomerDetailsDTO getOnlyCustomerByEmail (String email) throws Exception;
    
}
