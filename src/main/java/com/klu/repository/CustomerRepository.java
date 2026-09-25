package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}