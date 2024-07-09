package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.models.entities.RelayStateEnum;
import com.solarsmart.frontoffice.repositories.api.RelayRepository;
import com.solarsmart.frontoffice.services.api.RelaisService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RelaisServiceImpl extends BaseServiceImpl<RelaisState, RelayRepository> implements RelaisService {

    public RelaisServiceImpl(RelayRepository repository) {
        super(repository);
    }

    @Override
    public RelaisState getActive() {
        return repository.findByState(RelayStateEnum.HIGH);
    }

    @Override
    public RelaisState getByState(RelayStateEnum state) {
        return repository.findByState(state);
    }
}
