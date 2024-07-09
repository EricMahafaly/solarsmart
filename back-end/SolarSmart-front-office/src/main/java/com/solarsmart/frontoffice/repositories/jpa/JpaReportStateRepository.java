package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.ReportState;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface JpaReportStateRepository extends JpaRepository<ReportState, Long> {

    Optional<ReportState> findByState(ReportStateEnum state);
}