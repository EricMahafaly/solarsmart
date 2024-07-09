package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BatteryDataResponse {
    private Long id;
    private LocalDateTime date;
    private double tension;
    private double puissance;
    private double courant;
    private double energy;
    private double pourcentage;

    public BatteryDataResponse(BatteryData batteryData){

        this.id = batteryData.getId();
        this.date = batteryData.getDate();
        this.tension = batteryData.getTension();
        this.puissance = batteryData.getPuissance();
        this.courant = batteryData.getCourant();
        this.energy = batteryData.getEnergy();
        this.pourcentage = batteryData.getPourcentage();
    }

    public static BatteryDataResponse toPublic(BatteryData batteryData){
        BatteryDataResponse dataPublic = new BatteryDataResponse();
        dataPublic.id = batteryData.getId();
        dataPublic.date = batteryData.getDate();
        dataPublic.tension = batteryData.getTension();
        dataPublic.puissance = batteryData.getPuissance();
        dataPublic.courant = batteryData.getCourant();
        dataPublic.energy = batteryData.getEnergy();
        dataPublic.pourcentage = batteryData.getPourcentage();
        return dataPublic;
    }
}
