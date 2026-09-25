package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    boolean existsByCustomerIdAndPlanIdAndStatus(
        Long customerId,
        Long planId,
        String status
    );
}
