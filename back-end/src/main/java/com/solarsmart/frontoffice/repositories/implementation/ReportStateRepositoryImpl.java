package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.ReportState;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import com.solarsmart.frontoffice.repositories.api.ReportStateRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaReportStateRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ReportStateRepositoryImpl extends BaseRepositoryImpl<ReportState, JpaReportStateRepository> implements ReportStateRepository {
    public ReportStateRepositoryImpl(JpaReportStateRepository repository) {
        super(repository);
    }

    @Override
    public Optional<ReportState> findByState(ReportStateEnum state) {
        return this.repository.findByState(state);
    }
}
