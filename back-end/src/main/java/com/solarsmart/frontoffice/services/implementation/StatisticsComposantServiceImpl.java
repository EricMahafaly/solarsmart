package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.ComposantData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.repositories.api.ComposantDataRepository;
import com.solarsmart.frontoffice.services.helpers.DateHelper;
import com.solarsmart.frontoffice.services.api.StatisticsComposantService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
public class StatisticsComposantServiceImpl<C extends ComposantData> implements StatisticsComposantService {
    protected final ComposantDataRepository<C> composantDataRepository;

    @Override
    public List<ComposantDataInfo> findAllBetweenTimeByModule(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDate = date.atTime(startTime);
        LocalDateTime endDate = date.atTime(endTime);
        return this.composantDataRepository.getAllDetail(moduleId, startDate, endDate);
    }

    @Override
    public ComposantDataInfo findByTimeAndModule(long moduleId, LocalDateTime date) {
        LocalDateTime[] datIn = DateHelper.createTwoDateInOneMinute(date);
        return this.composantDataRepository.findByTimeAndModule(moduleId, datIn[0], datIn[1]);
    }
}
