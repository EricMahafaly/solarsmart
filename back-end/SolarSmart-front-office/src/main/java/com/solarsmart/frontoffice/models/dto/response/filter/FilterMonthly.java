package com.solarsmart.frontoffice.models.dto.response.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
//@NoArgsConstructor
public class FilterMonthly<T> extends FilterDate<T> {
    private int month;
    private long year;
    @JsonIgnore
    private List<FilterMonthly<T>> filterMonthlies;

    private FilterMonthly(int month, long year){
        this.setMonth(month);
        this.setLabel(month);
        this.setTag(month);
        this.setYear(year);
    }

    public static <T> FilterMonthly<T> getInstance(long year, T data) {
        return new FilterMonthly<>(year, data);
    }

    private FilterMonthly(long year, T data){
        this.setYear(year);
        this.setData(data);

        this.init(year, data);
    }
    private FilterMonthly(int month, long year, T data){
        this(month, year);

        this.setData(data);
    }



    private void init(long year){
        if (this.filterMonthlies == null){
            filterMonthlies = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                filterMonthlies.add(new FilterMonthly<>(i, year));
            }
        }
    }

    private void init(long year, T data){
        if (this.filterMonthlies == null){
            filterMonthlies = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                filterMonthlies.add(new FilterMonthly<>(i, year, data));
            }
        }
    }

    @Override
    public List<FilterMonthly<T>> getListResult(){
        return this.filterMonthlies;
    }


    public void setData(int month, T data) {
        int size = filterMonthlies.size();
        for (int i = 0; i < size; i++) {
            if (filterMonthlies.get(i).getMonth() == month && filterMonthlies.get(i).getYear() == year) {
                filterMonthlies.get(i).setData(data);
                break;
            }
        }
    }

    private String[] months(){
//        String[] months = new String[]{"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout",
//                "Septembre", "Octobre", "Novembre", "Décembre"};

        String[] months = new String[12];

        Month[] month1 = Month.values();

        for (int i = 0; i < month1.length; i++) {
            months[i] = month1[i].name();
        }


        return months;
    }

    private void setLabel(int month){
        this.setLabel(this.months()[month-1]);
    }

    private void setTag(int month) {
        String label = this.months()[month-1];
        String tag = label.substring(0, 1).toUpperCase();
    }
}
