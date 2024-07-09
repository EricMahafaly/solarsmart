package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.BatteryData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BatteryDataRegistration {
    private double tension;
    private double energy;
    private double puissance;
    private LocalDateTime date;
    private double courant;
    private double combinedSOC;

    public BatteryData toModel(Battery battery){
//        BatteryType type = battery.getBatteryType();

        BatteryData batteryData = new BatteryData();
        batteryData.setTension(this.getTension());
        batteryData.setEnergy(this.getEnergy());
        batteryData.setPuissance(this.getPuissance());
        batteryData.setDate(this.getDate());
        batteryData.setCourant(this.getCourant());
        batteryData.setPourcentage(this.combinedSOC);
//        batteryData.setPourcentage((tension * 100) / battery.getPuissance());
        batteryData.setBattery(battery);
        return batteryData;
    }

//    private double getPercentage(){
//        return (tension * 100)/type.getPuissance();
//    }
}
