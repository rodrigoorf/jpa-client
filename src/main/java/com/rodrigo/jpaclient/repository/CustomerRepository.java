package com.rodrigo.jpaclient.repository;

import com.rodrigo.jpaclient.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Page<Customer> findByAddresses_ZipCodeEquals(Optional<String> zipCode, Pageable pageable);

}