package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.PlanningBattery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPlanningBatteryRepository extends JpaRepository<PlanningBattery, Long> {

    @Query("select p from PlanningBattery p where p.dateDebut <= ?1 and ?1 < p.dateFin and p.battery.module.id = ?2 and p.done = false ")
    List<PlanningBattery> findAllByModuleIdAndNotDoneAndDateRange(LocalDateTime now, Long moduleId);

    @Query("select count(p) from PlanningBattery p where (p.dateDebut <= ?1 and ?1 < p.dateFin or p.energy <= ?3) and p.battery.module.id = ?2 and p.done = false ")
    long countByDateAndEnergyAndModuleIdAndNotDone(LocalDateTime now, Long moduleId, double energy);

    @Query("select count(p) from PlanningBattery p where (p.dateFin <= ?2  or p.energy >= ?3) and p.battery.module.id = ?1 and p.done = false ")
    long countByModuleIdAndDateOrEnergyAndNotDone(Long moduleId, LocalDateTime now, double energy);

    @Query("select count(p) from PlanningBattery p where p.battery.module.id = ?1 and p.done = false ")
    long countAllByNotDoneAdnModuleId(long moduleId);

    @Modifying
    @Query("update PlanningBattery p set p.done = true where ( p.dateFin <= ?1 or p.energy >= ?3 ) and p.done = false and p.battery.module.id = ?2 ")
    void markAllAsDoneByModuleIdAndNotDoneAndDateRangeOrEnergyLessEqual(LocalDateTime now, Long moduleId, double energy);


}
