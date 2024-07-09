package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Panel;
import lombok.Data;

@Data
public class PanelRegistration {
    private double puissance;
    private String marque;
    private double voltage;

    public Panel toModel(){
        Panel battery = new Panel();
        battery.setPuissance(this.getPuissance());
        battery.setMarque(this.getMarque());
        battery.setVoltage(this.getVoltage());

        return battery;
    }
}
