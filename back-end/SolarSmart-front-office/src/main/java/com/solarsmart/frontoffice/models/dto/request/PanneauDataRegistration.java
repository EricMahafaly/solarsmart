package com.solarsmart.frontoffice.models.dto.request;


import com.solarsmart.frontoffice.models.entities.PanelData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PanneauDataRegistration {

    private double production;

    private LocalDateTime date;

    private double tension;

    private double puissance;

    private double courant;

    public PanelData toModel(){
        PanelData panelData = new PanelData();
        panelData.setProduction(this.production);
        panelData.setDate(this.date);
        panelData.setTension(this.getTension());
        panelData.setPuissance(this.puissance);
        panelData.setCourant(this.courant);

        return panelData;
    }
}
