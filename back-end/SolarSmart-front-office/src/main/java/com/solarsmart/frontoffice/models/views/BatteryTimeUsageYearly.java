package com.solarsmart.frontoffice.models.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BatteryTimeUsageYearly {

    private Long batteryId;
    private int month;
    private double totalUsage;
    private long year;

    public BatteryTimeUsageYearly(Long moduleId, int month, double totalUsage, long year) {
        this.batteryId = moduleId;
        this.month = month;
        this.totalUsage = totalUsage;
        this.year = year;
    }

    public void setTotalUsage(double totalUsage) {
        this.totalUsage = (totalUsage/3600);
    }
}
