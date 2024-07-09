package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Report;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import com.solarsmart.frontoffice.repositories.api.ReportRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepositoryImpl extends BaseRepositoryImpl<Report, JpaReportRepository> implements ReportRepository {
    public ReportRepositoryImpl(JpaReportRepository repository) {
        super(repository);
    }

    @Override
    public Page<Report> getByAdminNullOrEqual(Long adminId,Pageable pageable) {
        return this.repository.findByAdminIdNullAndAdminId(adminId, pageable);
    }

    @Override
    public long countReportByState(ReportStateEnum state) {
        return this.repository.countByState_State(state);
    }
}
