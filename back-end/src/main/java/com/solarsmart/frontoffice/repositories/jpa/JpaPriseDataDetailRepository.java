package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.PriseData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface JpaPriseDataDetailRepository extends JpaComposantDataRepository<PriseData>, JpaRepository<PriseData, Long> {

    @Override
    @Query("select p.tension as tension, p.puissance as puissance, p.courant as courant, " +
            "p.date as date from PriseData p where p.prise.module.id = ?1 and p.date between ?2 and ?3 order by date asc ")
    List<ComposantDataInfo> getAllByModuleIdAndBetweenDate(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    @Query("select p.tension as tension, p.puissance as puissance, p.courant as courant, " +
            "p.date as date from PriseData p where p.date between ?2 and ?3 and p.prise.module.id = ?1")
    ComposantDataInfo findByTimeAndModule(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    @Query("select bd.courant as value, bd.date from PriseData bd where Date(bd.date) = ?2 and bd.prise.module.id = ?1")
    List<ComposantProjection> getAllCourantByDateAndModuleId(Long moduleId, LocalDate date);

    @Override
    @Query("select bd.puissance as value, bd.date from PriseData bd where Date(bd.date) = ?2 and bd.prise.module.id = ?1")
    List<ComposantProjection> getAllPuissanceByDateAndModuleId(Long moduleId, LocalDate date);

    @Override
    @Query("select bd.tension as value, bd.date from PriseData bd where Date(bd.date) = ?2 and bd.prise.module.id = ?1")
    List<ComposantProjection> getAllTensionByDateAndModuleId(Long moduleId, LocalDate date);

    @Override
    @Query("select pd from PriseData pd where pd.prise.module.id = ?1")
    List<PriseData> getAllByModuleId(Long moduleId);

}
