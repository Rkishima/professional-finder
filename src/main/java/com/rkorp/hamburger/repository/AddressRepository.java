package com.rkorp.hamburger.repository;

import com.rkorp.hamburger.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomerId(Long customerId);

    List<Address> deleteByCustomerId(Long customerId);
}
