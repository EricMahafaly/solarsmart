package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BatteryDataRepository extends BaseRepository<BatteryData>, ComposantDataRepository<BatteryData> {

//    BatteryDataService findByModuleIdAndBetweenDate(long moduleId, LocalDateTime date1, LocalDateTime date2);


    Page<ComposantProjection> getAllTensionPaginateByDateAndModuleId(long moduleId, LocalDate date, Pageable pageable);

    Page<BatteryData> findAllByModuleId(Long moduleId, Pageable pageable);

    List<BatteryData> findAll(long moduleId, LocalDateTime date1, LocalDateTime date2);
}
