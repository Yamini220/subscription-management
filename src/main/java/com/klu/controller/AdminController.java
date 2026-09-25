package com.klu.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.klu.Admin;
import com.klu.repository.AdminRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private final AdminRepository repository;

    public AdminController(AdminRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {

        Optional<Admin> existingAdmin =
                repository.findByUsername(admin.getUsername());

        if (existingAdmin.isEmpty()) {
            return "Invalid username or password";
        }

        Admin savedAdmin = existingAdmin.get();

        if (savedAdmin.getPassword()
                .equals(admin.getPassword())) {

            return "Login successful";
        }

        return "Invalid username or password";
    }
}