package com.klu.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.klu.Subscription;
import com.klu.repository.SubscriptionRepository;

@RestController
@RequestMapping("/subscriptions")
@CrossOrigin
public class SubscriptionController {

    private final SubscriptionRepository repository;

    public SubscriptionController(SubscriptionRepository repository) {
        this.repository = repository;
    }

    // ================= GET ALL SUBSCRIPTIONS =================
    @GetMapping
    public List<Subscription> getAllSubscriptions() {

        List<Subscription> subscriptions = repository.findAll();

        LocalDate today = LocalDate.now();

        /*
         * Automatically change ACTIVE subscriptions
         * to EXPIRED when the end date has passed.
         *
         * CANCELLED subscriptions are not changed.
         */
        for (Subscription subscription : subscriptions) {

            if ("ACTIVE".equals(subscription.getStatus())
                    && subscription.getEndDate() != null
                    && subscription.getEndDate().isBefore(today)) {

                subscription.setStatus("EXPIRED");

                repository.save(subscription);
            }
        }

        return repository.findAll();
    }


    // ================= CREATE SUBSCRIPTION =================
    @PostMapping
    public Subscription addSubscription(
            @RequestBody Subscription subscription) {

        boolean alreadyExists =
                repository.existsByCustomerIdAndPlanIdAndStatus(
                        subscription.getCustomerId(),
                        subscription.getPlanId(),
                        "ACTIVE"
                );

        if (alreadyExists) {

            throw new RuntimeException(
                    "Customer already has an active subscription to this plan"
            );
        }

        if (subscription.getStatus() == null
                || subscription.getStatus().isEmpty()) {

            subscription.setStatus("ACTIVE");
        }

        return repository.save(subscription);
    }


    // ================= UPDATE SUBSCRIPTION =================
    @PutMapping("/{id}")
    public Subscription updateSubscription(
            @PathVariable Long id,
            @RequestBody Subscription updatedSubscription) {

        Subscription subscription =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subscription not found"
                                )
                        );

        subscription.setCustomerId(
                updatedSubscription.getCustomerId()
        );

        subscription.setPlanId(
                updatedSubscription.getPlanId()
        );

        subscription.setStartDate(
                updatedSubscription.getStartDate()
        );

        subscription.setEndDate(
                updatedSubscription.getEndDate()
        );

        if (updatedSubscription.getStatus() != null
                && !updatedSubscription.getStatus().isEmpty()) {

            subscription.setStatus(
                    updatedSubscription.getStatus()
            );
        }

        return repository.save(subscription);
    }


    // ================= CANCEL SUBSCRIPTION =================
    @PutMapping("/{id}/cancel")
    public Subscription cancelSubscription(
            @PathVariable Long id) {

        Subscription subscription =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subscription not found"
                                )
                        );

        subscription.setStatus("CANCELLED");

        return repository.save(subscription);
    }


    // ================= REACTIVATE SUBSCRIPTION =================
    @PutMapping("/{id}/reactivate")
    public Subscription reactivateSubscription(
            @PathVariable Long id) {

        Subscription subscription =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subscription not found"
                                )
                        );

        subscription.setStatus("ACTIVE");

        return repository.save(subscription);
    }


    // ================= DELETE SUBSCRIPTION =================
    @DeleteMapping("/{id}")
    public void deleteSubscription(
            @PathVariable Long id) {

        repository.deleteById(id);
    }
}