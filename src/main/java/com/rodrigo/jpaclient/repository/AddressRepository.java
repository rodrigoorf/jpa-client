package com.rodrigo.jpaclient.repository;

import com.rodrigo.jpaclient.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByZipCodeEqualsAndNumberEquals(String zipCode, String number);
}