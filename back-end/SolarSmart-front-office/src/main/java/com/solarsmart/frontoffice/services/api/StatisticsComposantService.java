package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface StatisticsComposantService {

    List<ComposantDataInfo> findAllBetweenTimeByModule(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime);
    ComposantDataInfo findByTimeAndModule(long moduleId, LocalDateTime date);


}
