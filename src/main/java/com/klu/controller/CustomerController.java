package com.klu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.klu.Customer;
import com.klu.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer updatedCustomer) {

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());

        return repository.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
    }
}