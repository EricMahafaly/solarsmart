package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.projection.BatteryDistinct;
import lombok.Data;

@Data
public class BatteryDistinctResponse {
    private String marque;
    private Double puissance;
    private Double voltage;

    public BatteryDistinctResponse(BatteryDistinct batteryDistinct) {
        this.marque = batteryDistinct.getMarque();
        this.puissance = batteryDistinct.getPuissance();
        this.voltage = batteryDistinct.getVoltage();
    }
}
