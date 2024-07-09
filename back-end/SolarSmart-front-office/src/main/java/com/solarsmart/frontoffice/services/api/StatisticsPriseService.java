package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface StatisticsPriseService extends StatisticsComposantService {
    List<FilterMonthly<Double>> getConsommationByModuleId(long moduleId, long month, long year);

    double getConsommationBetweenTimeAndModuleId(long moduleId, LocalTime time1, LocalTime time2, LocalDate date);

    List<ComposantProjection> getConsommationsBetweenDateByModuleId(
            long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime);

    List<FilterMonthly<Double>> getConsommationInYear(long moduleId, long year);
    List<FilterWeekly<Double>> getConsommationWeeklyInMonth(long moduleId, int month, long year);
}
