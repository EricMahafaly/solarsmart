package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Report;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import com.solarsmart.frontoffice.repositories.api.ReportRepository;
import com.solarsmart.frontoffice.services.api.ReportService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends BaseServiceImpl<Report, ReportRepository> implements ReportService {
    public ReportServiceImpl(ReportRepository repository) {
        super(repository);
    }

    @Override
    public Page<Report> getAllByAdmin(long adminId, Pageable pageable) {
        return this.repository.getByAdminNullOrEqual(adminId, pageable);
    }

    @Override
    public long countReportUnsolved() {
        return this.repository.countReportByState(ReportStateEnum.IN_PROGRESS);
    }
}
