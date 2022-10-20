package com.rkorp.hamburger.service;

import com.rkorp.hamburger.exception.ResourceNotFoundException;
import com.rkorp.hamburger.model.Customer;


public interface CustomerService {

    public abstract Customer saveCustomer(Customer customer) throws ResourceNotFoundException;
}
