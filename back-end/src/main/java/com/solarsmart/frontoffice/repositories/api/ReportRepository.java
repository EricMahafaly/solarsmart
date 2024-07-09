package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Report;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ReportRepository extends BaseRepository<Report> {

    Page<Report> getByAdminNullOrEqual(Long adminId,Pageable pageable);

    long countReportByState(ReportStateEnum state);
}
