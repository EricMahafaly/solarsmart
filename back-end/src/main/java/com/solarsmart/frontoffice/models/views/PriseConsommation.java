package com.solarsmart.frontoffice.models.views;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
public class PriseConsommation {
    private Long moduleId;
    private long year;
    private int month;
    private LocalDate date;
    private double consommation;

    public PriseConsommation(Long moduleId, long year, int month, Date date, double consommation) {
        this.moduleId = moduleId;
        this.year = year;
        this.month = month;
        this.date = date.toLocalDate();
        this.consommation = consommation;
    }

//    public static void main(String[] args) {
//        LocalDate date = LocalDate.of(2024, 1, 22);
//        LocalDate date1 = LocalDate.of(2024, 4, 22);
//
//        long differenceEnJours = ChronoUnit.DAYS.between(date, date1);
//
//        System.out.println(differenceEnJours);
//    }
}
