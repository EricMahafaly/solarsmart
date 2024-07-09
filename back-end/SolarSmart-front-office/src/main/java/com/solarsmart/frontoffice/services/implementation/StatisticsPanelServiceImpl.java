package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.models.views.PanelUsageDaily;
import com.solarsmart.frontoffice.models.views.PanelUsageMonthly;
import com.solarsmart.frontoffice.repositories.api.PanelDataRepository;
import com.solarsmart.frontoffice.repositories.api.PanelProductionRepository;
import com.solarsmart.frontoffice.services.api.PanelService;
import com.solarsmart.frontoffice.services.api.StatisticsPanelService;
import com.solarsmart.frontoffice.services.helpers.DateHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class StatisticsPanelServiceImpl extends StatisticsComposantServiceImpl<PanelData> implements StatisticsPanelService {
    private final PanelProductionRepository panelProductionRepository;
    private final PanelDataRepository panelDataRepository;
    private final PanelService panelService;
//    private panel

    public StatisticsPanelServiceImpl(PanelDataRepository panelDataRepository, PanelProductionRepository panelProductionRepository, PanelService panelService) {
        super(panelDataRepository);
        this.panelProductionRepository = panelProductionRepository;
        this.panelDataRepository = panelDataRepository;
        this.panelService = panelService;
    }

    @Override
    public List<FilterMonthly<Double>> getProductionInYear(long moduleId, long year) {
        FilterMonthly<Double> filterMonthly = FilterMonthly.getInstance(year, 0.0);

        long panelId = this.panelService.getByModuleId(moduleId).getId();

        List<PanelUsageMonthly> panelUsageMonthlies =
                panelProductionRepository.findAllByPanelIdAndYear(panelId, year);

        for (PanelUsageMonthly panelUsageMonthly : panelUsageMonthlies) {
            filterMonthly.setData(panelUsageMonthly.getMonth(), panelUsageMonthly.getProduction());
        }
        return filterMonthly.getListResult();
    }

    @Override
    public FilterWeekly<Double> getProductionInWeek(long moduleId, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public FilterWeekly<Double> getProductionInWeek(long moduleId, int semaineNumber, int month, long year) {
        return null;
    }

    @Override
    public List<FilterWeekly<Double>> getProductionWeeklyInMonth(long moduleId, int month, long year) {
        FilterWeekly<Double> filterWeekly = FilterWeekly.getInstance(month, year, 0.0);

        long panelId = this.panelService.getByModuleId(moduleId).getId();

        List<PanelUsageDaily> panelUsageDailies = this.panelProductionRepository
                .findAllByPanelIdAndMonthAndYear(panelId, month, year);

        for (PanelUsageDaily panelUsageDaily : panelUsageDailies) {
            filterWeekly.setData(panelUsageDaily.getDate(), panelUsageDaily.getProduction());
        }
        return filterWeekly.getListResult();
    }

    @Override
    public double findProductionBetweenTimeAndModuleId(long moduleId, LocalTime startTime, LocalTime endTime, LocalDate date) {

        LocalDateTime date1 = DateHelper.assemble(startTime, date);
        LocalDateTime date2 = DateHelper.assemble(endTime, date);
        double energy = panelDataRepository.getProductionBetweenTimeAndModuleId(moduleId, date1, date2);

        return energy;
    }

    @Override
    public List<ComposantProjection> getProductionBetweenDateByModuleId(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDate = date.atTime(startTime);
        LocalDateTime endDate = date.atTime(endTime);

        return panelDataRepository.getAllProductionBetweenDateByModuleId(moduleId, startDate, endDate);
    }
}
