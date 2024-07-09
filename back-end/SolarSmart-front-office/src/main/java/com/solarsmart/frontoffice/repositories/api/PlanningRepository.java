package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Planning;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanningRepository<T extends Planning> extends BaseRepository<T> {

    List<T> getPlanningByDateAfter(Long moduleId, LocalDateTime dateTime);

    void markAllDoneByCondition(Long moduleId, LocalDateTime time, double courant);

    long countAllNotDoneDateBetweenStartAndEnd(long moduleId, LocalDateTime time, double courant);

    long countAllNotDoneAndDateAfterEnd(long moduleId, LocalDateTime date, double courant);

    long countAllNotDone(long moduleId);
}
