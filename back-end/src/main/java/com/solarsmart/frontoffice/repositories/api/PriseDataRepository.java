package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.PriseData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriseDataRepository extends BaseRepository<PriseData>, ComposantDataRepository<PriseData> {

    List<ComposantProjection> getConsommationsBetweenDateByModuleId(Long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    double getConsommationBetweenTimeAndModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2);

    List<PriseData> findAll(long moduleId, LocalDateTime date1, LocalDateTime date2);
}
