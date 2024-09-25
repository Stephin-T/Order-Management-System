package com.training.task.mapper;

import org.springframework.stereotype.Component;
import com.training.task.dto.CustomerDetailsDTO;
import com.training.task.entity.Customer;

@Component
public class CustomerMapper {

    public CustomerDetailsDTO toCustomerDetailsDTO(Customer customer) {
        return customer == null ? null : new CustomerDetailsDTO() {{
            setCustomerId(customer.getCustomerId());
            setFirstName(customer.getFirstName());
            setMiddleName(customer.getMiddleName());
            setLastName(customer.getLastName());
            setEmail(customer.getEmail());
            setCompany(customer.getCompany());
            setBillingStreet(customer.getBillingStreet());
            setBillingCity(customer.getBillingCity());
            setBillingState(customer.getBillingState());
            setBillingZip(customer.getBillingZip());
        }};
    }
}
