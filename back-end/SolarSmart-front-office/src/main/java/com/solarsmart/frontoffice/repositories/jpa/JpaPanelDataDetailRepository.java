package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface JpaPanelDataDetailRepository extends JpaComposantDataRepository<PanelData> {

    @Override
    @Query("select p.tension as tension, p.puissance as puissance, p.courant as courant, " +
            "p.date as date from PanelData p where p.panel.module.id = ?1 and p.date between ?2 and ?3 order by date asc ")
    List<ComposantDataInfo> getAllByModuleIdAndBetweenDate(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    @Query("select p.tension as tension, p.puissance as puissance, p.courant as courant, " +
            "p.date as date from PanelData p where p.date between ?2 and ?3 and p.panel.module.id = ?1")
    ComposantDataInfo findByTimeAndModule(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    @Query("select bd from PanelData bd where bd.panel.module.id = ?1")
    List<PanelData> getAllByModuleId(Long moduleId);

    @Override
    @Query("select bd.courant as value, bd.date from PanelData bd where Date(bd.date) = ?2 and bd.panel.module.id = ?1")
    List<ComposantProjection> getAllCourantByDateAndModuleId(Long moduleId, LocalDate date);

    @Override
    @Query("select bd.puissance as value, bd.date from PanelData bd where Date(bd.date) = ?2 and bd.panel.module.id = ?1")
    List<ComposantProjection> getAllPuissanceByDateAndModuleId(Long moduleId, LocalDate date);
    @Override
    @Query("select bd.tension as value, bd.date from PanelData bd where Date(bd.date) = ?2 and bd.panel.module.id = ?1")
    List<ComposantProjection> getAllTensionByDateAndModuleId(Long moduleId, LocalDate date);


}
