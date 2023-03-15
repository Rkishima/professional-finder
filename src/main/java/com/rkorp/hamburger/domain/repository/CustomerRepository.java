package com.rkorp.hamburger.domain.repository;

import com.rkorp.hamburger.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);
    Optional<Customer> findByEmail(String email);
}
