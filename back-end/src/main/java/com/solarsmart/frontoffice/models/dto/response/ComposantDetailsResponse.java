package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComposantDetailsResponse {
    private double tension;
    private double puissance;
    private double courant;
    private LocalDateTime date;

    public ComposantDetailsResponse(ComposantDataInfo composantDataInfo){
        if (composantDataInfo != null){
            this.setTension(composantDataInfo.getTension());
            this.setCourant(composantDataInfo.getCourant());
            this.setDate(composantDataInfo.getDate());
            this.setPuissance(composantDataInfo.getPuissance());
        }
    }
}
