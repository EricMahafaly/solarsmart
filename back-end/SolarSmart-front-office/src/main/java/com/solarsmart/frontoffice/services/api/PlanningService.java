package com.solarsmart.frontoffice.services.api;

import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;

@NoRepositoryBean
public interface PlanningService {

    void markAllPlanningDoneByCondition(long moduleId, LocalDateTime time, double courant);
}
