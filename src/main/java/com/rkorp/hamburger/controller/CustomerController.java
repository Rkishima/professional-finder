package com.rkorp.hamburger.controller;

import com.rkorp.hamburger.exception.ResourceNotFoundException;
import com.rkorp.hamburger.model.Customer;
import com.rkorp.hamburger.repository.CustomerRepository;
import com.rkorp.hamburger.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id = " + customerId));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customer")
    public Customer createCustomer(@Valid @RequestBody Customer customer) throws ResourceNotFoundException {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId, @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id = " + customerId));

        customer.setName(customerDetails.getName());
        customer.setAge(customerDetails.getAge());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);

    }

    @DeleteMapping("/customer/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id = " + customerId));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
