package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Battery;
import lombok.Data;

@Data
public class BatteryRegistration {
    private double puissance;
    private String marque;
    private double voltage;

    public Battery toModel(){
        Battery battery = new Battery();
        battery.setPuissance(this.getPuissance());
        battery.setMarque(this.getMarque());
        battery.setVoltage(this.getVoltage());

        return battery;
    }
}
