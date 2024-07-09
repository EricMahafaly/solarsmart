package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.models.entities.projection.BatteryTimeUsage;
import com.solarsmart.frontoffice.models.views.BatteryTimeUsageYearly;
import com.solarsmart.frontoffice.models.views.TimeUsageDaily;
import com.solarsmart.frontoffice.repositories.api.BatteryTimeUsageRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaBatteryDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BatteryTimeUsageRepositoryImpl implements BatteryTimeUsageRepository {
    private final JpaBatteryDataRepository jpaBatteryDataRepository;

//    @PersistenceContext
//    private final EntityManager entityManager;
    @Override
    public List<BatteryTimeUsageYearly> getTimeUsageMonthly(long year, long moduleId) {
        List<BatteryData> dataList = jpaBatteryDataRepository.getTimeUsagePerMonth(year, moduleId);

        Map<YearMonth, Double> usageHoursByMonth = new HashMap<>();

        for (int i = 1; i < dataList.size(); i++) {

            BatteryData currentData = dataList.get(i);
            BatteryData previousData = dataList.get(i - 1);

            if (currentData.getCourant() != 0 && previousData.getCourant() != 0) {
                if (currentData.getDate().toLocalDate().equals(previousData.getDate().toLocalDate())) {
                    Duration duration = Duration.between(previousData.getDate(), currentData.getDate());
                    double hours = duration.toMinutes() / 60.0;

                    YearMonth yearMonth = YearMonth.from(currentData.getDate());
                    usageHoursByMonth.merge(yearMonth, hours, Double::sum);
                }
            }

        }

        List<BatteryTimeUsageYearly> batteryTimeUsageMonthly = new ArrayList<>();
        for (Map.Entry<YearMonth, Double> entry : usageHoursByMonth.entrySet()) {
            YearMonth yearMonth = entry.getKey();
            double totalUsage = entry.getValue();
            batteryTimeUsageMonthly.add(new BatteryTimeUsageYearly(moduleId, yearMonth.getMonthValue(), totalUsage, yearMonth.getYear()));
        }

        return batteryTimeUsageMonthly;
    }


    @Override
    public List<TimeUsageDaily> getTimeUsageWeeklyInMonth(long moduleId, long year, int month) {

//        List<BatteryTimeUsage> batteryTimeUsages = jpaBatteryDataRepository.getTimeUsageDaily(moduleId, year, month);
        List<BatteryData> data = jpaBatteryDataRepository.getTimeUsagePerDay(moduleId, year, month);

        Map<LocalDate, Double> usageHoursByDay = new HashMap<>();

        for (int i = 1; i < data.size(); i++) {
            BatteryData current = data.get(i);
            BatteryData previous = data.get(i - 1);

            if (current.getCourant() != 0 && previous.getCourant() != 0) {
                if (current.getDate().toLocalDate().equals(previous.getDate().toLocalDate())) {
                    Duration duration = Duration.between(previous.getDate(), current.getDate());
                    double hours = duration.toMinutes() / 60.0;

                    LocalDate day = current.getDate().toLocalDate();
                    usageHoursByDay.merge(day, hours, Double::sum);
                }
            }
        }

        List<TimeUsageDaily> batteryTimeUsageDailies = new ArrayList<>();

        for (Map.Entry<LocalDate, Double> entry : usageHoursByDay.entrySet()) {
            LocalDate day = entry.getKey();
            double totalUsage = entry.getValue();
            batteryTimeUsageDailies.add(new TimeUsageDaily(moduleId, day, totalUsage));
        }

//        for (int i = 1; i < batteryTimeUsages.size(); i++) {
//            BatteryData currentData = batteryTimeUsages.get(i);
//
//            if (currentData.getCourant() > 0){
//                BatteryData previousData = batteryTimeUsages.get(i - 1);
//
//                // Calculer la différence de temps entre les deux enregistrements
//                Duration duration = Duration.between(previousData.getDate(), currentData.getDate());
//
//                TimeUsageDaily timeUsageDaily = new TimeUsageDaily(moduleId,
//                        currentData.getDate().toLocalDate(), Math.abs(duration.getSeconds()));
//
//                batteryTimeUsageDailies.add(timeUsageDaily);
//            }
//        }

        return batteryTimeUsageDailies;
    }

    @Override
    public TimeUsageDaily getTimeUsageByModuleAndDate(long moduleId, LocalDate date) {

        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = date.atTime(23, 59, 59);
//        LocalDateTime endDate = date.atTime()

        List<BatteryTimeUsage> batteryTimeUsages = this.jpaBatteryDataRepository.getTimeUsageBetweenDate(moduleId, startDate, endDate);

        double durationDouble = 0.;

        for (int i = 1; i < batteryTimeUsages.size(); i++) {
            BatteryTimeUsage previousData = batteryTimeUsages.get(i - 1);
            BatteryTimeUsage currentData = batteryTimeUsages.get(i);

            // Calculer la différence de temps entre les deux enregistrements
            Duration duration = Duration.between(previousData.getDate(), currentData.getDate());

            durationDouble += Math.abs(duration.getSeconds());

        }

        return new TimeUsageDaily(moduleId,
                date, durationDouble);
    }
}
