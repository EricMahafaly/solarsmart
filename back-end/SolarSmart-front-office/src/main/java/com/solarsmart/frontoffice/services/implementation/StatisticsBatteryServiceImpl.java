package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.models.views.BatteryTimeUsageYearly;
import com.solarsmart.frontoffice.models.views.TimeUsageDaily;
import com.solarsmart.frontoffice.repositories.api.BatteryDataRepository;
import com.solarsmart.frontoffice.repositories.api.BatteryRepository;
import com.solarsmart.frontoffice.repositories.api.BatteryTimeUsageRepository;
import com.solarsmart.frontoffice.services.api.BatteryService;
import com.solarsmart.frontoffice.services.api.StatisticsBatteryService;
import com.solarsmart.frontoffice.services.helpers.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class StatisticsBatteryServiceImpl extends StatisticsComposantServiceImpl<BatteryData> implements StatisticsBatteryService {
    private final BatteryRepository batteryRepository;
    private final BatteryService batteryService;
    private final BatteryTimeUsageRepository timeUsageRepository;
    public StatisticsBatteryServiceImpl(BatteryRepository batteryRepository, BatteryService batteryService, BatteryDataRepository batteryDataRepository, BatteryTimeUsageRepository timeUsageRepository) {
        super(batteryDataRepository);
        this.batteryService = batteryService;
        this.batteryRepository = batteryRepository;
        this.timeUsageRepository = timeUsageRepository;
    }

    @Override
    public double getCurrentPercentageByModuleId(long moduleId) {
        this.batteryService.getByModule(moduleId);
        Double percentage = batteryRepository.getCurrentPercentageByModuleId(moduleId);
        return percentage != null ? percentage : 0.0;
    }

    @Override
    public double getEnergyBetweenTimeAndModuleId(long moduleId, LocalTime time1, LocalTime time2, LocalDate date) {
        LocalDateTime date1 = DateHelper.assemble(time1, date);
        LocalDateTime date2 = DateHelper.assemble(time2, date);
        return batteryRepository.getEnergyBetweenTimeAndModuleId(moduleId, date1, date2);
    }

    @Override
    public List<ComposantProjection> getEnergiesBetweenDateByModuleId(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDate = DateHelper.assemble(startTime, date);
        LocalDateTime endDate = DateHelper.assemble(endTime, date);
        return batteryRepository.getEnergiesBetweenDateByModuleId(moduleId, startDate, endDate);
    }

    @Override
    public List<FilterMonthly<Double>> getTimeUsageInYear(long moduleId, long year) {
        FilterMonthly<Double> monthly = FilterMonthly.getInstance(year, 0.0);
        List<BatteryTimeUsageYearly> batteryUsageYearlies = timeUsageRepository.getTimeUsageMonthly(year, moduleId);
        log.info("battery usage monthly: {}", batteryUsageYearlies);
        for (BatteryTimeUsageYearly batteryTimeUsageYearly : batteryUsageYearlies){
            monthly.setData(batteryTimeUsageYearly.getMonth(), batteryTimeUsageYearly.getTotalUsage());
        }
        return monthly.getListResult();
    }

    @Override
    public FilterWeekly<Double> getTimeUsageWeeklyInMonth(long moduleId, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public FilterWeekly<Double> getTimeUsageWeeklyInMonth(long moduleId, int semaineNumber, int month, long year) {
        return null;
    }

    @Override
    public List<FilterWeekly<Double>> getTimeUsageInMonth(long moduleId, int month, long year) {
        FilterWeekly<Double> filterWeekly = FilterWeekly.getInstance(month, year, 0.0);

        List<TimeUsageDaily> batteryTimeUsages = this.timeUsageRepository.getTimeUsageWeeklyInMonth(moduleId, year, month);

        for (TimeUsageDaily batteryTimeUsage : batteryTimeUsages) {
            filterWeekly.setData(batteryTimeUsage.getDate(), batteryTimeUsage.getDuration());
        }

        return filterWeekly.getListResult();
    }
}
