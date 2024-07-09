package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface JpaBatteryDataDetailRepository extends JpaComposantDataRepository<BatteryData> {

    @Override
    @Query("select b.tension as tension, b.puissance as puissance, b.courant as courant, " +
            "b.date as date from BatteryData b where b.battery.module.id = ?1 and b.date between ?2 and ?3 order by date asc ")
    List<ComposantDataInfo> getAllByModuleIdAndBetweenDate(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    @Query("select coalesce(b.tension, 0) as tension, coalesce(b.puissance, 0) as puissance, " +
            "coalesce(b.courant, 0) as courant, b.date as date from BatteryData b " +
            "where b.date between ?2 and ?3 and b.battery.module.id = ?1 order by b.date")
    ComposantDataInfo findByTimeAndModule(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    @Query("select bd from BatteryData bd where bd.battery.module.id = ?1")
    List<BatteryData> getAllByModuleId(Long moduleId);

    @Override
    @Query("select bd.courant as value, bd.date from BatteryData bd where Date(bd.date) = ?2 and bd.battery.module.id = ?1")
    List<ComposantProjection> getAllCourantByDateAndModuleId(Long moduleId, LocalDate date);

    @Override
    @Query("select bd.puissance as value, bd.date from BatteryData bd where Date(bd.date) = ?2 and bd.battery.module.id = ?1")
    List<ComposantProjection> getAllPuissanceByDateAndModuleId(Long moduleId, LocalDate date);

    @Override
    @Query("select bd.tension as value, bd.date from BatteryData bd where Date(bd.date) = ?2 and bd.battery.module.id = ?1")
    List<ComposantProjection> getAllTensionByDateAndModuleId(Long moduleId, LocalDate date);
}
