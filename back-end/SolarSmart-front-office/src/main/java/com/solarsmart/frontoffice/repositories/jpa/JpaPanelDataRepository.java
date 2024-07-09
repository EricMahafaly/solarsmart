package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPanelDataRepository extends JpaRepository<PanelData, Long> {
    PanelData findPanelDataByPanel_Module_IdAndDateBetween(Long moduleId, LocalDateTime date, LocalDateTime date2);

    @Query("SELECT coalesce(SUM(b.production), 0) FROM PanelData b WHERE b.panel.module.id = ?1 AND b.date BETWEEN ?2 AND ?3 ")
    Double getProductionByModuleIdAndDateBetween(Long moduleId, LocalDateTime date, LocalDateTime date2);


    @Query("select bd.production as value, bd.date as date from PanelData bd where bd.date between ?2 and ?3 and bd.panel.module.id = ?1")
    List<ComposantProjection> getAllProductionBetweenDateByModuleId(Long moduleId, LocalDateTime startDate, LocalDateTime endDate);

//    @Query(nativeQuery = true, value = "select * from v_panel_production_usage_daily " +
//            "where id = :moduleId and extract(MONTH from date) = :month and extract(YEAR from date) = :year")
//    List<Tuple> getPanelDailyUsage(@Param("moduleId") Long moduleId, @Param("year")Long year, @Param("month")int month);


    @Query(nativeQuery = true, value = "select * from v_panel_production_usage_daily " +
            "where id = :panelId and extract(MONTH from date) = :month and extract(YEAR from date) = :year")
    List<Tuple> getPanelDailyUsage(@Param("panelId") Long panelId, @Param("year")Long year, @Param("month")int month);

    @Query(nativeQuery = true, value = "select * from v_panel_production_usage_monthly " +
            "where id = :panelId and year = :year")
    List<Tuple> getPanelMonthlyUsage(@Param("panelId") Long panelId, @Param("year")Long year);

    List<PanelData> findByPanel_Module_IdAndDateBetween(Long moduleId, LocalDateTime dateStart, LocalDateTime dateEnd);
}
