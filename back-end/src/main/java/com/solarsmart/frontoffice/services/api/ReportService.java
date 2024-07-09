package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Report;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService extends BaseService<Report> {

    Page<Report> getAllByAdmin(long adminId, Pageable pageable);

    long countReportUnsolved();
}
