package com.rodrigo.jpaclient.service;

import com.rodrigo.jpaclient.entity.Address;
import com.rodrigo.jpaclient.entity.Customer;
import com.rodrigo.jpaclient.exception.CustomerNotFoundException;
import com.rodrigo.jpaclient.exception.InvalidZipCodeException;
import com.rodrigo.jpaclient.repository.AddressRepository;
import com.rodrigo.jpaclient.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rodrigo.jpaclient.utils.AddressUtils.ZIP_CODE_PATTERN_MESSAGE;
import static com.rodrigo.jpaclient.utils.AddressUtils.ZIP_CODE_REGEXP;
import static com.rodrigo.jpaclient.utils.CustomerUtils.CUSTOMER_NOT_FOUND_MESSAGE;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Customer createCustomer(Customer customer) {
        customer.setRegistrationDate(LocalDate.now());
        customer.setLastUpdateInfo(Timestamp.from(Instant.now()));
        customer.setAddresses(validateAddresses(customer.getAddresses()));
        return customerRepository.save(customer);
    }

    public Page<Customer> getCustomers(Pageable pageable, Optional<String> zipCode) throws InvalidZipCodeException {
        if (zipCode.isPresent()) {
            if (isZipCodeValid(zipCode.get())) {
                return customerRepository.findByAddresses_ZipCodeEquals(zipCode, pageable);
            }
            else {
                throw new InvalidZipCodeException(ZIP_CODE_PATTERN_MESSAGE);
            }
        } else {
            return customerRepository.findAll(pageable);
        }

    }

    public Optional<Customer> getCustomerById(int id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer;
        } else {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE);
        }
    }

    public Customer updateCustomer(int id, Customer newCustomer) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer existentCustomer = customer.get();
            existentCustomer.setAddresses(validateAddresses(newCustomer.getAddresses()));
            existentCustomer.setAge(newCustomer.getAge());
            existentCustomer.setName(newCustomer.getName());
            existentCustomer.setLastUpdateInfo(Timestamp.from(Instant.now()));
            return customerRepository.save(existentCustomer);
        } else {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE);
        }
    }

    public void deleteCustomer(int id) throws CustomerNotFoundException {
        getCustomerById(id);
        customerRepository.deleteById(id);
    }

    private List<Address> validateAddresses(List<Address> addresses) {
        List<Address> existingAddresses = new ArrayList<>();
        List<Address> inexistingAddresses = new ArrayList<>();
        for (Address address : addresses) {
            Optional<Address> queryResult = addressRepository.findByZipCodeEqualsAndNumberEquals(address.getZipCode(), address.getNumber());
            if (queryResult.isPresent()) {
                existingAddresses.add(queryResult.get());
            } else {
                inexistingAddresses.add(address);
            }
        }
        List<Address> finalList = new ArrayList<>();
        finalList.addAll(existingAddresses);
        finalList.addAll(inexistingAddresses);
        return finalList;
    }

    private boolean isZipCodeValid(String zipCode) {
        return zipCode.matches(ZIP_CODE_REGEXP);
    }
}
