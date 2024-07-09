package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.PriseData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriseDataRegistration {

    private double consommation;
    private LocalDateTime date;
    private double tension;
    private double puissance;
    private double courant;
    public PriseData toModel(){
        PriseData priseData = new PriseData();
        priseData.setDate(this.getDate());
        priseData.setTension(this.getTension());
        priseData.setPuissance(this.getPuissance());
        priseData.setConsommation(this.getConsommation());
        priseData.setCourant(this.getCourant());

        return priseData;
    }
}
