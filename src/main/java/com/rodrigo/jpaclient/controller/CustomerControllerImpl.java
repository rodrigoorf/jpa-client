package com.rodrigo.jpaclient.controller;

import com.rodrigo.jpaclient.entity.Customer;
import com.rodrigo.jpaclient.exception.CustomerNotFoundException;
import com.rodrigo.jpaclient.exception.InvalidZipCodeException;
import com.rodrigo.jpaclient.service.CustomerService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/customers")
public class CustomerControllerImpl implements CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(@Valid @RequestBody Customer customer) {
        Customer customerCreated = service.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCreated);
    }

    @GetMapping
    public ResponseEntity getCustomers(
            @ParameterObject Pageable pageable,
            @RequestParam(value = "zipCode", required = false) Optional<String> zipCode) throws InvalidZipCodeException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCustomers(pageable, zipCode));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getCustomerById(@PathVariable int id) throws CustomerNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCustomerById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateCustomer(@PathVariable int id, @Valid @RequestBody Customer customer) throws CustomerNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCustomer(id, customer));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCustomer(@PathVariable int id) throws CustomerNotFoundException {
        service.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
