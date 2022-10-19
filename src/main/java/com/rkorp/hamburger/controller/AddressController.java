package com.rkorp.hamburger.controller;

import com.rkorp.hamburger.exception.ResourceNotFoundException;
import com.rkorp.hamburger.model.Address;
import com.rkorp.hamburger.model.Customer;
import com.rkorp.hamburger.repository.AddressRepository;
import com.rkorp.hamburger.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/{customerId}/address")
    public ResponseEntity<List<Address>> getAllAddressByCustomerId(@PathVariable(value = "customerId") Long customerId) throws ResourceNotFoundException {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Not found customer with id = " + customerId);
        }

        List<Address> addressList = addressRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") Long addressId) throws ResourceNotFoundException {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("address not found for this id = " + addressId));
        return ResponseEntity.ok().body(address);
    }

    @PostMapping("customer/{customerId}/address")
    public ResponseEntity<Address> createAddress(@PathVariable(value = "customerId") Long customerId, @RequestBody Address addressRequest) throws ResourceNotFoundException {
        Address address = customerRepository.findById(customerId).map(customer -> {
            addressRequest.setCustomer(customer);
            return addressRepository.save(addressRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found customer with id = " + customerId));
        return new ResponseEntity<>(address, HttpStatus.OK);

    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Address> updateaddress(@PathVariable(value = "id") Long addressId, @Valid @RequestBody Address addressDetails) throws ResourceNotFoundException {

        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("address not found for this id = " + addressId));

        address.setBuildingNumber(addressDetails.getBuildingNumber());
        address.setCep(addressDetails.getCep());
        address.setStreetName(addressDetails.getStreetName());
        address.setCountry(addressDetails.getCountry());
        address.setState(addressDetails.getState());
        address.setTown(addressDetails.getTown());
        final Address updateaddress = addressRepository.save(address);
        return ResponseEntity.ok(updateaddress);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") Long addressId) {
        addressRepository.deleteById(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @DeleteMapping("customer/{customerId}/address")
    public ResponseEntity<List<Address>> deleteAllAddressOfCustomer(@PathVariable(value = "customerId") Long customerId) throws ResourceNotFoundException {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Not found customer with id = " + customerId);
        }

        addressRepository.deleteByCustomerId(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
