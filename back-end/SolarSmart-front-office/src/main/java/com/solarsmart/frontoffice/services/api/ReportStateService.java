package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.ReportState;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import com.solarsmart.frontoffice.services.api.base.BaseService;

import java.util.Optional;

public interface ReportStateService extends BaseService<ReportState> {

    Optional<ReportState> findByState(ReportStateEnum state);
}
