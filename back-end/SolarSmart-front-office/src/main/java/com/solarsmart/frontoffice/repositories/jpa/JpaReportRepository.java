package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.Report;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JpaReportRepository extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report> {
    @Query("select r from Report r where r.admin.id is null or r.admin.id = ?1")
    Page<Report> findByAdminIdNullAndAdminId(Long id, Pageable pageable);

    @Query("select count(r) from Report r where r.state.state = ?1")
    long countByState_State(ReportStateEnum state);


}