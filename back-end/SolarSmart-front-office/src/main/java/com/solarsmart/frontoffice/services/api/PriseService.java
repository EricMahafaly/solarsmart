package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.services.api.base.BaseService;

public interface PriseService extends BaseService<Prise> {
    Prise getByModuleId(long idModule);
}
