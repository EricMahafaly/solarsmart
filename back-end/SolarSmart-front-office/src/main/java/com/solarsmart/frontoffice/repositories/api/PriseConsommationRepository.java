package com.solarsmart.frontoffice.repositories.api;


import com.solarsmart.frontoffice.models.views.PriseConsommation;

import java.time.LocalDate;
import java.util.List;

public interface PriseConsommationRepository {
    List<PriseConsommation> findAllByModuleIdAndDateBetween(long moduleId, LocalDate date1, LocalDate date2);

    List<PriseConsommation> findAllByModuleIdAndMonthAndYear(long moduleId, int month, long year);
    List<PriseConsommation> findAllByModuleIdAndYear(long moduleId, long year);
}
