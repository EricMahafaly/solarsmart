package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.PlanningBattery;
import com.solarsmart.frontoffice.models.entities.PlanningPrise;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlanningRegistration {

    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    public PlanningBattery toPlanningBatteryModel(){
        PlanningBattery planning = new PlanningBattery();
        planning.setDateDebut(this.dateDebut);
        planning.setDateFin(this.dateFin);

        return planning;
    }

    public PlanningPrise toPlanningPriseModel(){
        PlanningPrise planning = new PlanningPrise();
        planning.setDateDebut(this.dateDebut);
        planning.setDateFin(this.dateFin);

        return planning;
    }
}
