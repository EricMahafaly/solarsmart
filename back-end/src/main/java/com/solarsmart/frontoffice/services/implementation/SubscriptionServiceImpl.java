package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Subscription;
import com.solarsmart.frontoffice.repositories.api.SubscriptionRepository;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl extends BaseServiceImpl<Subscription, SubscriptionRepository> implements BaseService<Subscription> {
    public SubscriptionServiceImpl(SubscriptionRepository repository) {
        super(repository);
    }
}
