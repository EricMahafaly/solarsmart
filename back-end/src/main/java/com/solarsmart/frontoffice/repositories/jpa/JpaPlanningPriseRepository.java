package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.PlanningPrise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPlanningPriseRepository extends JpaRepository<PlanningPrise, Long> {

    @Query("select p from PlanningPrise p where p.dateDebut <= ?1 and ?1 < p.dateFin and p.prise.module.id = ?2 and p.done = false ")
    List<PlanningPrise> findAllByModuleIdAndNotDoneAndDateRange(LocalDateTime now, Long moduleId);

    @Query("select count(p) from PlanningPrise p where (p.dateDebut <= ?1 and ?1 < p.dateFin or p.consommation <= ?3) and p.prise.module.id = ?2 and p.done = false ")
    long countByDateAndEnergyAndModuleIdAndNotDone(LocalDateTime now, Long moduleId, double consommation);

    @Query("select count(p) from PlanningPrise p where (p.dateFin <= ?2  or p.consommation >= ?3) and p.prise.module.id = ?1 and p.done = false ")
    long countByModuleIdAndDateOrEnergyAndNotDone(Long moduleId, LocalDateTime now, double consommation);

    @Query("select count(p) from PlanningPrise p where p.prise.module.id = ?1 and p.done = false ")
    long countAllByNotDoneAdnModuleId(long moduleId);

    @Modifying
    @Query("update PlanningPrise p set p.done = true where ( p.dateFin <= ?1 or p.consommation >= ?3 ) and p.done = false and p.prise.module.id = ?2 ")
    void markAllAsDoneByModuleIdAndNotDoneAndDateRangeOrEnergyLessEqual(LocalDateTime now, Long moduleId, double consommation);
}
