package com.rkorp.hamburger.domain.service;

import com.rkorp.hamburger.domain.exception.BusinessException;
import com.rkorp.hamburger.domain.model.Customer;
import com.rkorp.hamburger.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer saveCustomer(Customer customer){
        boolean emailInUse = customerRepository.findByEmail(customer.getEmail()).stream().anyMatch(existingCustomer -> !existingCustomer.equals(customer));
        boolean cpfinUse = customerRepository.findByCpf(customer.getCpf()).stream().anyMatch(existingCustomer -> !existingCustomer.equals(customer));
        if (emailInUse){
            throw new BusinessException("Já existe um cliente com este e-email.");
        } else if (cpfinUse){
            throw new BusinessException("Já existe um cliente com este CPF cadastrado.");
        }
        return customerRepository.save(customer);
    }
}
