package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.PlanningBattery;
import com.solarsmart.frontoffice.repositories.api.PlanningBatteryRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaPlanningBatteryRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PlanningBatteryRepositoryImpl extends BaseRepositoryImpl<PlanningBattery, JpaPlanningBatteryRepository> implements PlanningBatteryRepository {

    public PlanningBatteryRepositoryImpl(JpaPlanningBatteryRepository repository) {
        super(repository);
    }

    @Override
    public List<PlanningBattery> getPlanningByDateAfter(Long moduleId, LocalDateTime dateTime) {
        return repository.findAllByModuleIdAndNotDoneAndDateRange(dateTime, moduleId);
    }

    @Override
    public void markAllDoneByCondition(Long moduleId, LocalDateTime time, double energy) {
        repository.markAllAsDoneByModuleIdAndNotDoneAndDateRangeOrEnergyLessEqual(time, moduleId, energy);
    }

    @Override
    public long countAllNotDoneDateBetweenStartAndEnd(long moduleId, LocalDateTime time, double energy) {
        return this.repository.countByDateAndEnergyAndModuleIdAndNotDone(time, moduleId, energy);
    }

    @Override
    public long countAllNotDoneAndDateAfterEnd(long moduleId, LocalDateTime date, double energy) {
//        return this.repository.countAllByModuleIdAndNotDoneAndDateRangeOrEnergyLessEqual(date, moduleId, energy);
        return this.repository.countByModuleIdAndDateOrEnergyAndNotDone(moduleId, date, energy);
    }

    @Override
    public long countAllNotDone(long moduleId) {
        return repository.countAllByNotDoneAdnModuleId(moduleId);
    }
}
