package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.PlanningPrise;
import com.solarsmart.frontoffice.repositories.api.PlanningPriseRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaPlanningPriseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PlanningPriseRepositoryImpl extends BaseRepositoryImpl<PlanningPrise, JpaPlanningPriseRepository> implements PlanningPriseRepository {

    public PlanningPriseRepositoryImpl(JpaPlanningPriseRepository repository) {
        super(repository);
    }


    @Override
    public List<PlanningPrise> getPlanningByDateAfter(Long moduleId, LocalDateTime dateTime) {
        return repository.findAllByModuleIdAndNotDoneAndDateRange(dateTime, moduleId);
    }

    @Override
    public void markAllDoneByCondition(Long moduleId, LocalDateTime time, double courant) {
        repository.markAllAsDoneByModuleIdAndNotDoneAndDateRangeOrEnergyLessEqual(time, moduleId, courant);
    }

    @Override
    public long countAllNotDoneDateBetweenStartAndEnd(long moduleId, LocalDateTime time, double courant) {
        return this.repository.countByDateAndEnergyAndModuleIdAndNotDone(time, moduleId, courant);
    }

    @Override
    public long countAllNotDoneAndDateAfterEnd(long moduleId, LocalDateTime date, double courant) {
        return this.repository.countByModuleIdAndDateOrEnergyAndNotDone(moduleId, date, courant);
    }

    @Override
    public long countAllNotDone(long moduleId) {
        return repository.countAllByNotDoneAdnModuleId(moduleId);
    }
}
