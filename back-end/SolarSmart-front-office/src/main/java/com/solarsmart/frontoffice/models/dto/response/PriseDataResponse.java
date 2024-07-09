package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.PriseData;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PriseDataResponse {
    private long id;
    private LocalDateTime date;
    private double tension;
    private double puissance;
    private double courant;
    private double consommation;

    public static PriseDataResponse toPublic(PriseData priseData){
        PriseDataResponse dataPublic = new PriseDataResponse();
        dataPublic.id = priseData.getId();
        dataPublic.date = priseData.getDate();
        dataPublic.tension = priseData.getTension();
        dataPublic.puissance = priseData.getPuissance();
        dataPublic.courant = priseData.getCourant();
        dataPublic.consommation = priseData.getConsommation();

        return dataPublic;
    }

    public static List<PriseDataResponse> toPublic(List<PriseData> data){
        return data
                .stream()
                .map(PriseDataResponse::toPublic)
                .collect(Collectors.toList());
    }
}
