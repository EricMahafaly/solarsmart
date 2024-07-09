package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.projection.BatteryDistinct;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BatteryRepository extends BaseRepository<Battery> {

    Optional<Battery> findByModuleId(long moduleId);

    Double getCurrentPercentageByModuleId(long moduleId);

    double getEnergyBetweenTimeAndModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2);

    List<ComposantProjection> getEnergiesBetweenDateByModuleId(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    List<BatteryDistinct> getAllUnique();
}
