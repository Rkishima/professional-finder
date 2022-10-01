package com.rkorp.hamburger.repository;

import com.rkorp.hamburger.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {
}
