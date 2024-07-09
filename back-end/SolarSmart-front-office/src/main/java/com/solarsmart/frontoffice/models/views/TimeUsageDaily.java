package com.solarsmart.frontoffice.models.views;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeUsageDaily {
    private Long moduleId;
    private LocalDate date;
    private double duration;

    public TimeUsageDaily(Long moduleId, Date date, double duration) {
        this.moduleId = moduleId;
        this.date = date.toLocalDate();
//        this.setDuration(duration);
        this.duration = duration;
    }

    public void setDuration(double duration) {
        this.duration = (duration/3600);
    }
}
