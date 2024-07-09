package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.*;
import com.solarsmart.frontoffice.services.api.base.BaseService;

public interface RelaisService extends BaseService<RelaisState> {
    RelaisState getActive();

    RelaisState getByState(RelayStateEnum state);
}
