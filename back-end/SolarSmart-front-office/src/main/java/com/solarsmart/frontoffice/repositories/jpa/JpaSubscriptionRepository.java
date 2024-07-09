package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSubscriptionRepository extends JpaRepository<Subscription, Long> {
}