package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.views.TimeUsageDaily;

import java.time.LocalDate;
import java.util.Optional;

public interface PriseTimeUsageRepository {


    Optional<TimeUsageDaily> getTimeUsageByModuleAndDate(long moduleId, LocalDate date);
}
