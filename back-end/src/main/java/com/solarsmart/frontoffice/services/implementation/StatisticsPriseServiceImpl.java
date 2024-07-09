package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.PriseData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.models.views.PriseConsommation;
import com.solarsmart.frontoffice.repositories.api.PriseConsommationRepository;
import com.solarsmart.frontoffice.repositories.api.PriseDataRepository;
import com.solarsmart.frontoffice.services.api.StatisticsPriseService;
import com.solarsmart.frontoffice.services.helpers.DateHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class StatisticsPriseServiceImpl extends StatisticsComposantServiceImpl<PriseData> implements StatisticsPriseService {
    private final PriseDataRepository priseDataRepository;

    private final PriseConsommationRepository priseConsommationRepository;

    public StatisticsPriseServiceImpl(PriseDataRepository composantDataRepository, PriseConsommationRepository priseConsommationRepository) {
        super(composantDataRepository);
        this.priseDataRepository = composantDataRepository;
        this.priseConsommationRepository = priseConsommationRepository;
    }

    @Override
    public List<FilterMonthly<Double>> getConsommationByModuleId(long moduleId, long month, long year) {
//        priseConsommationRepository.findAllByDateBetween()
        return null;
    }

    @Override
    public double getConsommationBetweenTimeAndModuleId(long moduleId, LocalTime time1, LocalTime time2, LocalDate date) {
        LocalDateTime date1 = DateHelper.assemble(time1, date);
        LocalDateTime date2 = DateHelper.assemble(time2, date);

//        double consommation = 0;
        double consommation = priseDataRepository.getConsommationBetweenTimeAndModuleId(moduleId, date1, date2);
        return consommation;
    }

    @Override
    public List<ComposantProjection> getConsommationsBetweenDateByModuleId(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDate = date.atTime(startTime);
        LocalDateTime endDate = date.atTime(endTime);
        return priseDataRepository.getConsommationsBetweenDateByModuleId(moduleId, startDate, endDate);
    }

    @Override
    public List<FilterMonthly<Double>> getConsommationInYear(long moduleId, long year) {
//        List<PriseConsommation> consommations = priseConsommationRepository.findAllByModuleIdAndYear(moduleId, year);
        List<PriseConsommation> consommations = priseConsommationRepository.findAllByModuleIdAndYear(moduleId, year);
        FilterMonthly<Double> filterMonthly= FilterMonthly.getInstance(year, 0.0);
        for (PriseConsommation consommation : consommations) {
            filterMonthly.setData(consommation.getMonth(), consommation.getConsommation());
        }
        return filterMonthly.getListResult();
    }

    @Override
    public List<FilterWeekly<Double>> getConsommationWeeklyInMonth(long moduleId, int month, long year) {
//        List<PriseConsommation> consommations = priseConsommationRepository.findAllByModuleIdAndMonthAndYear(moduleId, month, year);
        List<PriseConsommation> consommations = priseConsommationRepository.findAllByModuleIdAndMonthAndYear(moduleId, month, year);
        FilterWeekly<Double> filterWeekly = FilterWeekly.getInstance(month, year, 0.0);
        for (PriseConsommation consommation : consommations) {
            filterWeekly.setData(consommation.getDate(), consommation.getConsommation());
        }
        return filterWeekly.getListResult();
    }
}
