package com.solarsmart.frontoffice.services.helpers;

import com.solarsmart.frontoffice.models.entities.projection.BatteryTimeUsage;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public final class DateHelper {

    public static LocalDateTime[] createTwoDateInOneMinute(LocalDateTime referenceDate){
        LocalDateTime[] dateResult = new LocalDateTime[2];
        dateResult[0] = referenceDate.withSecond(0);
        dateResult[1] = referenceDate.withSecond(59);

        return dateResult;
    }

    public static LocalDateTime assemble(LocalTime time, LocalDate date){
        return time.atDate(date);
    }

    public long calculateDelay(LocalDateTime scheduledDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, scheduledDateTime);
        return Math.max(duration.getSeconds(), 0);
    }

    public static List<LocalDate> getAllDatesInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startMonth = yearMonth.atDay(1);
        LocalDate endMonth = yearMonth.atEndOfMonth();
        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = startMonth; !date.isAfter(endMonth); date = date.plusDays(1)) {
            dates.add(date);
        }
        return dates;
    }

    public static double getDuration(LocalDateTime startDate, LocalDateTime endDate){

        // Calculer la différence de temps entre les deux enregistrements
        Duration duration = Duration.between(startDate, endDate);

        return Math.abs(duration.getSeconds());
    }

    public static LocalTime toLocalTime(double hour){
        // Extraire la partie entière et la partie décimale
        int heures = (int) hour; // Partie entière
        int minutes = (int) ((hour - heures) * 60); // Conversion de la partie décimale en minutes

        // Créer un objet LocalTime avec les heures et les minutes
        LocalTime heure = LocalTime.of(heures, minutes);

        return heure;
    }
}
