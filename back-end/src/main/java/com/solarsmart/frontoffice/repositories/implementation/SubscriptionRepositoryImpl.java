package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Subscription;
import com.solarsmart.frontoffice.repositories.api.SubscriptionRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaSubscriptionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionRepositoryImpl extends BaseRepositoryImpl<Subscription, JpaSubscriptionRepository> implements SubscriptionRepository {
    public SubscriptionRepositoryImpl(JpaSubscriptionRepository repository) {
        super(repository);
    }
}
