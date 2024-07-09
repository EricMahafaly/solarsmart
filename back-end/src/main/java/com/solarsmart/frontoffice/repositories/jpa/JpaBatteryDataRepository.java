package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.models.entities.projection.BatteryTimeUsage;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaBatteryDataRepository extends JpaRepository<BatteryData, Long> {
    Page<BatteryData> findByBattery_Id(Long batteryId, Pageable pageable);

    Page<BatteryData> findByBattery_Module_Id(long moduleId, Pageable pageable);

    @Query("SELECT coalesce(bd.pourcentage, 0) FROM BatteryData bd WHERE bd.battery.module.id = :moduleId " +
            "ORDER BY bd.date DESC limit 1")
    Double findPourcentageForLatestDateByModuleId(@Param("moduleId") Long moduleId);

    BatteryData findBatteryDataByBattery_Module_IdAndDateBetween(Long moduleId, LocalDateTime date, LocalDateTime date2);
    @Query("SELECT coalesce(SUM(b.energy), 0) FROM BatteryData b WHERE b.battery.module.id = ?1 AND b.date BETWEEN ?2 AND ?3 ")
    double getEnergyByModuleIdAndDateBetween(Long moduleId, LocalDateTime date, LocalDateTime date2);

    List<BatteryData> findAllByDateBetweenAndBattery_Module_IdOrderByDateAsc(LocalDateTime date, LocalDateTime date2, Long moduleId);

    @Query("select bd.energy as value, bd.date as date from BatteryData bd where bd.date between ?2 and ?3 and bd.battery.module.id = ?1")
    List<ComposantProjection> getAllEnergyBetweenDateByModuleId(Long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("select bd.tension as value, bd.date from BatteryData bd where Date(bd.date) = :date and bd.battery.module.id = :moduleId")
    Page<ComposantProjection> getAllTensionByDateAndModuleId(@Param("moduleId") Long moduleId, @Param("date") LocalDate date, Pageable pageable);

    @Query( "select b.battery.module.id as moduleId, b.date as date from BatteryData b" +
            " where b.battery.module.id = ?1 and extract(MONTH from b.date) = ?3 and extract(YEAR from b.date) = ?2 order by date")
    List<BatteryTimeUsage> getTimeUsageDaily(Long moduleId, long year, int month);

    @Query( "select b from BatteryData b" +
            " where b.battery.module.id = ?1 and extract(MONTH from b.date) = ?3 and extract(YEAR from b.date) = ?2 order by b.date")
    List<BatteryData> getTimeUsagePerDay(Long moduleId, long year, int month);


    @Query( "select b.battery.module.id as moduleId, b.date as date from BatteryData b" +
            " where b.battery.module.id = ?1 and b.date >= ?2 and extract(YEAR from b.date) < ?3 order by date")
    List<BatteryTimeUsage> getTimeUsageBetweenDate(Long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("select bd from BatteryData bd where " +
            "extract(year from bd.date) = ?1 and bd.battery.module.id = ?2 order by bd.date")
    List<BatteryData> getTimeUsagePerMonth(long year, long moduleId);


}
