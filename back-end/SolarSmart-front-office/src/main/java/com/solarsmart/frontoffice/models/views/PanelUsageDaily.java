package com.solarsmart.frontoffice.models.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PanelUsageDaily {

    private Long moduleId;
    private Double production;
    private LocalDate date;

    public PanelUsageDaily(Long moduleId, Double production, Date date) {
        this.moduleId = moduleId;
        this.production = production;
        this.date = date.toLocalDate();
    }
}
