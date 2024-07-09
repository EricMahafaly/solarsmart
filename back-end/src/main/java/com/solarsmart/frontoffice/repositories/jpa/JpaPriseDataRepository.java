package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.PriseData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPriseDataRepository extends JpaRepository<PriseData, Long> {

    @Query("select sum(pd.consommation) as consommation from PriseData pd where pd.prise.module.id = ?1")
    double calculTotalConsommationByModuleId(long moduleId);

    PriseData findPriseDataByPrise_Module_IdAndDateBetween(Long moduleId, LocalDateTime date, LocalDateTime date2);

    @Query("SELECT coalesce(SUM(b.consommation), 0) FROM PriseData b WHERE b.prise.module.id = ?1 AND b.date BETWEEN ?2 AND ?3 ")
    double getConsommationByModuleIdAndDate(Long moduleId, LocalDateTime date, LocalDateTime date2);

    @Query("select bd.consommation as value, bd.date as date from PriseData bd where bd.date between ?2 and ?3 and bd.prise.module.id = ?1")
    List<ComposantProjection> getAllConsommationsBetweenDateByModuleId(Long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    List<PriseData> findByPrise_Module_IdAndDateBetween(Long id, LocalDateTime dateStart, LocalDateTime dateEnd);
}
