package com.training.task.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.training.task.dto.CustomerDTO;
import com.training.task.dto.CustomerDetailsDTO;
import com.training.task.entity.Customer;
import com.training.task.exception.ResourceNotFoundException;
import com.training.task.mapper.CustomerMapper;
import com.training.task.mapper.Mapper;
import com.training.task.repo.CustomerRepository;
import com.training.task.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final Mapper mapper;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, Mapper mapper, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.toCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return mapper.toCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + customerId));
        return mapper.toCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(mapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with id " + customerId);
        }

        Customer customer = mapper.toCustomer(customerDTO);
        customer.setCustomerId(customerId);
        Customer updatedCustomer = customerRepository.save(customer);
        return mapper.toCustomerDTO(updatedCustomer);
    }
    
    @Override
    public List<CustomerDetailsDTO> getAllCustomerDetails() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> customerMapper.toCustomerDetailsDTO(customer))
                .collect(Collectors.toList());
    }

	@Override
	public CustomerDTO getCustomerByEmail(String email) throws Exception {
		Customer customer = customerRepository.findByEmail(email);
		if(customer == null) {
			throw new Exception("Username "+email+" not registered");
		}
		return mapper.toCustomerDTO(customer);
	}

	@Override
	public CustomerDetailsDTO getOnlyCustomerByEmail(String email) throws Exception {
		Customer customer = customerRepository.findByEmail(email);
		if(customer == null) {
			throw new Exception("Username "+email+" not registered");
		}
		return customerMapper.toCustomerDetailsDTO(customer);
	}
}
