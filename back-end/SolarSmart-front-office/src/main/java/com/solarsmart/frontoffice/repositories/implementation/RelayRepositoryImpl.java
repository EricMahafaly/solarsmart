package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.models.entities.RelayStateEnum;
import com.solarsmart.frontoffice.repositories.api.RelayRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaRelayStateRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RelayRepositoryImpl extends BaseRepositoryImpl<RelaisState, JpaRelayStateRepository> implements RelayRepository {
    public RelayRepositoryImpl(JpaRelayStateRepository repository) {
        super(repository);
    }

    @Override
    public RelaisState findByState(RelayStateEnum state) {
        return repository.findByState(state);
    }
}
