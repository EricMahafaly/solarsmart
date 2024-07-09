package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.PanelData;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PanelDataResponse {
    private Long id;
    private double production;
    private LocalDateTime date;
    private double tension;
    private double puissance;
    private double courant;

    public static PanelDataResponse toPublic(PanelData panelData){
        PanelDataResponse panelDataResponse = new PanelDataResponse();
        panelDataResponse.setId(panelData.getId());
        panelDataResponse.setProduction(panelData.getProduction());
        panelDataResponse.setDate(panelData.getDate());
        panelDataResponse.setTension(panelData.getTension());
        panelDataResponse.setPuissance(panelData.getPuissance());
        panelDataResponse.setCourant(panelData.getCourant());

        return panelDataResponse;
    }

    public static List<PanelDataResponse> toPublic(List<PanelData> panelData){
        List<PanelDataResponse> panelDataResponse = panelData
                .stream()
                .map(PanelDataResponse::toPublic)
                .collect(Collectors.toList());

        return panelDataResponse;
    }


}
