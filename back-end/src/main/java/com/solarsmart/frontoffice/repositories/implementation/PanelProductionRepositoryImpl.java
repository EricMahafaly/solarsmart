package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.views.PanelUsageDaily;
import com.solarsmart.frontoffice.models.views.PanelUsageMonthly;
import com.solarsmart.frontoffice.repositories.api.PanelProductionRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaPanelDataRepository;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PanelProductionRepositoryImpl implements PanelProductionRepository {
    private final JpaPanelDataRepository jpaPanelDataRepository;
    @Override
    public List<PanelUsageDaily> findAllByModuleIdAndMonthAndYear(long moduleId, int month, long year) {

        List<Tuple> tuples = jpaPanelDataRepository.getPanelDailyUsage(moduleId, year, month);
        List<PanelUsageDaily> testPanelUsageDailies = new ArrayList<>();

        for (Tuple tuple : tuples) {
            Long moduleIdVal = tuple.get("id", Long.class);
            double production = tuple.get("production", Double.class);
            Date date = tuple.get("date", Date.class);

            testPanelUsageDailies.add(new PanelUsageDaily(moduleIdVal, production, date));
        }

        return testPanelUsageDailies;
    }

    @Override
    public List<PanelUsageDaily> findAllByPanelIdAndMonthAndYear(long panelId, int month, long year) {
        List<Tuple> tuples = jpaPanelDataRepository.getPanelDailyUsage(panelId, year, month);
        List<PanelUsageDaily> testPanelUsageDailies = new ArrayList<>();

        for (Tuple tuple : tuples) {
            Long moduleIdVal = tuple.get("id", Long.class);
            double production = tuple.get("production", Double.class);
            Date date = tuple.get("date", Date.class);

            testPanelUsageDailies.add(new PanelUsageDaily(moduleIdVal, production, date));
        }

        return testPanelUsageDailies;
    }

    @Override
    public List<PanelUsageMonthly> findAllByPanelIdAndYear(long panelId, long year) {
        List<Tuple> tuples = jpaPanelDataRepository.getPanelMonthlyUsage(panelId, year);
        List<PanelUsageMonthly> testPanelUsageMonthlies = new ArrayList<>();

        for (Tuple tuple : tuples) {
            Long moduleIdVal = tuple.get("id", Long.class);
            double production = tuple.get("production", Double.class);
            int month = tuple.get("month", Integer.class);
            long yearVal = tuple.get("year", Long.class);

            testPanelUsageMonthlies.add(new PanelUsageMonthly(moduleIdVal, production, month, yearVal));
        }

        return testPanelUsageMonthlies;
    }
}
