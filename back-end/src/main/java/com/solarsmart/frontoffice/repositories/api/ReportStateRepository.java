package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.ReportState;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.util.Optional;

public interface ReportStateRepository extends BaseRepository<ReportState> {

    Optional<ReportState> findByState(ReportStateEnum state);
}
