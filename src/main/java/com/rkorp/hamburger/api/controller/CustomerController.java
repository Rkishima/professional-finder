package com.rkorp.hamburger.api.controller;

import com.rkorp.hamburger.domain.model.Customer;
import com.rkorp.hamburger.domain.repository.CustomerRepository;
import com.rkorp.hamburger.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable(value = "id") Long customerId)  {
        return customerRepository.findById(customerId).map(customer -> ResponseEntity.ok(customer)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@Valid @RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId, @Valid @RequestBody Customer customer) {

        if(!customerRepository.existsById(customerId)){
            return ResponseEntity.notFound().build();
        }
        customer.setId(customerId);
        customer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        if (!customerRepository.existsById(customerId)){
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}
