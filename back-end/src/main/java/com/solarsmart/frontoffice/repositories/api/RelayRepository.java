package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.models.entities.RelayStateEnum;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

public interface RelayRepository extends BaseRepository<RelaisState> {

    RelaisState findByState(RelayStateEnum state);
}
