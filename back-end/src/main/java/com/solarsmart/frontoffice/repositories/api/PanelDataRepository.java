package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PanelDataRepository extends BaseRepository<PanelData>, ComposantDataRepository<PanelData> {
    double getProductionBetweenTimeAndModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2);

    List<ComposantProjection> getAllProductionBetweenDateByModuleId(
            long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    List<PanelData> findAll(long moduleId, LocalDateTime date1, LocalDateTime date2);


}
