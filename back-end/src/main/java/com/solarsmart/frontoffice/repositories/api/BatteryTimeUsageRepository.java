package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.views.BatteryTimeUsageYearly;
import com.solarsmart.frontoffice.models.views.TimeUsageDaily;

import java.time.LocalDate;
import java.util.List;

public interface BatteryTimeUsageRepository {
    List<BatteryTimeUsageYearly> getTimeUsageMonthly(long year, long moduleId);

    List<TimeUsageDaily> getTimeUsageWeeklyInMonth(long moduleId, long year, int month);

    TimeUsageDaily getTimeUsageByModuleAndDate(long moduleId, LocalDate date);
}
