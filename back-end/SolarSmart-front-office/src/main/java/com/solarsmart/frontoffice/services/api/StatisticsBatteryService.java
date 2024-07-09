package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface StatisticsBatteryService extends StatisticsComposantService {
    double getCurrentPercentageByModuleId(long moduleId);

    double getEnergyBetweenTimeAndModuleId(long moduleId, LocalTime time1, LocalTime time2, LocalDate date);

    List<ComposantProjection> getEnergiesBetweenDateByModuleId(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime);

    List<FilterMonthly<Double>> getTimeUsageInYear(long moduleId, long year);

    FilterWeekly<Double> getTimeUsageWeeklyInMonth(long moduleId, LocalDate startDate, LocalDate endDate);

    FilterWeekly<Double> getTimeUsageWeeklyInMonth(long moduleId, int semaineNumber, int month, long year);
    List<FilterWeekly<Double>> getTimeUsageInMonth(long moduleId, int month, long year);
}
