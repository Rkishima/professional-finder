package com.rkorp.hamburger.service.serviceImpl;

import com.rkorp.hamburger.exception.ResourceNotFoundException;
import com.rkorp.hamburger.model.Customer;
import com.rkorp.hamburger.repository.CustomerRepository;
import com.rkorp.hamburger.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) throws ResourceNotFoundException {
        boolean existCpf = customerRepository.findByCpf(customer.getCpf()).stream().anyMatch(existCustomer -> !existCustomer.equals(customer));
        boolean existEmail = customerRepository.findByEmail(customer.getEmail()).stream().anyMatch(existCustomer -> !existCustomer.equals(customer));
        if (existCpf){
            throw new ResourceNotFoundException("This CPF is already in use");
        } else if (existEmail){
            throw new ResourceNotFoundException("This email is already in use");
        }
        return customerRepository.save(customer);
    }
}
