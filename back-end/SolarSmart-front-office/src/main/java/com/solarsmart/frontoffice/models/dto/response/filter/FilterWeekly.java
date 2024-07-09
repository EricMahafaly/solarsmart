package com.solarsmart.frontoffice.models.dto.response.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class FilterWeekly<T> extends FilterDate<T> {
    private int number;
    private List<FilterDaily<T>> days;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonIgnore
    private List<FilterWeekly<T>> filterWeeklies;

    private List<FilterWeekly<T>> getFilterWeeklies(){
        return this.filterWeeklies;
    }

    private FilterWeekly(int month, long year, T data){
        this.init(month, year, data);
    }

    @Override
    @JsonProperty("total")
    public T getData() {
        return super.getData();
    }

    private FilterWeekly(){}


    public static <T> FilterWeekly<T> getInstance(int month, long year, T data){
        FilterWeekly<T> filterWeekly = new FilterWeekly<>(month, year, data);
        return filterWeekly;
    }

    public void init(int month, long year, T data) {
        if (this.filterWeeklies == null){
            this.filterWeeklies = new ArrayList<>();
            LocalDate firstDayOfMonth = LocalDate.of((int) year, month, 1);
            LocalDate lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1);

            LocalDate currentDate = firstDayOfMonth;
            int weekNumber = 1;

            while (currentDate.isBefore(lastDayOfMonth) || currentDate.isEqual(lastDayOfMonth)) {
                LocalDate startOfWeek = currentDate;
                LocalDate endOfWeek = startOfWeek.with(DayOfWeek.SUNDAY);
                if (endOfWeek.isAfter(lastDayOfMonth)) {
                    endOfWeek = lastDayOfMonth;
                }

                FilterWeekly<T> week = new FilterWeekly<>();
                week.setLabel("semaine "+ weekNumber);
                week.setNumber(weekNumber);
                week.setStartDate(startOfWeek);
                week.setEndDate(endOfWeek);
                week.setData(data);
                week.setTag("S"+weekNumber);

                FilterDaily<T> day = new FilterDaily<>();
//                day.init(week.getStartDate(), week.getEndDate(), data);
                day.init(startOfWeek, endOfWeek, data);

                week.setDays(day.getListResult());

                this.filterWeeklies.add(week);

                currentDate = endOfWeek.plusDays(1);
                weekNumber++;
            }
        }
    }

    public void setData(LocalDate date, T data){
        int size = this.filterWeeklies.size();
        for (int i = 0; i < size; i++) {
            FilterWeekly<T> filterWeekly = this.filterWeeklies.get(i);
            List<FilterDaily<T>> days = filterWeekly.getDays();
            int daysSize = days.size();
            for (int j = 0; j < daysSize; j++) {
                if (date.isEqual(days.get(j).getDate())) days.get(j).setData(data);
            }
        }
    }

    @Override
    public List<FilterWeekly<T>> getListResult() {
        return this.filterWeeklies;
    }

    public static void main(String[] args) {
        FilterWeekly<Double> filterWeekly = new FilterWeekly<>(1, 2024, 0.0);
        for (FilterWeekly<Double> weekly : filterWeekly.getListResult()) {
            System.out.println(weekly);
            for (FilterDaily<Double> day : weekly.getDays()) {
                System.out.println(day.getTag());
            }
        }
    }
}
