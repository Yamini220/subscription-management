package com.klu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.klu.Plan;
import com.klu.repository.PlanRepository;

@RestController
@RequestMapping("/plans")
@CrossOrigin
public class PlanController {

    private final PlanRepository repository;

    public PlanController(PlanRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Plan> getAllPlans() {
        return repository.findAll();
    }

    @PostMapping
    public Plan addPlan(@RequestBody Plan plan) {
        return repository.save(plan);
    }

    @PutMapping("/{id}")
    public Plan updatePlan(
            @PathVariable Long id,
            @RequestBody Plan updatedPlan) {

        Plan plan = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        plan.setName(updatedPlan.getName());
        plan.setPrice(updatedPlan.getPrice());

        // Save the subscription duration
        plan.setBillingCycle(updatedPlan.getBillingCycle());

        return repository.save(plan);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        repository.deleteById(id);
    }
}