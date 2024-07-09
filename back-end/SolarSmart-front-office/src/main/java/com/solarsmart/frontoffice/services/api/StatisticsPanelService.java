package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface StatisticsPanelService extends StatisticsComposantService {

    List<FilterMonthly<Double>> getProductionInYear(long moduleId, long year);

    FilterWeekly<Double> getProductionInWeek(long moduleId, LocalDate startDate, LocalDate endDate);


    FilterWeekly<Double> getProductionInWeek(long moduleId, int semaineNumber, int month, long year);
    List<FilterWeekly<Double>> getProductionWeeklyInMonth(long moduleId, int month, long year);

    double findProductionBetweenTimeAndModuleId(long moduleId, LocalTime startTime, LocalTime endTime, LocalDate date);

    List<ComposantProjection> getProductionBetweenDateByModuleId(
            long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime);
}
