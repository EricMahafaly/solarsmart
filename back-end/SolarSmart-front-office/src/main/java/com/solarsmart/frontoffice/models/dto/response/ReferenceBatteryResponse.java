package com.solarsmart.frontoffice.models.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solarsmart.frontoffice.models.entities.ReferenceBattery;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ReferenceBatteryResponse {
    private long id;

    private LocalTime duration;

    private Double energy;


    public ReferenceBatteryResponse(ReferenceBattery referenceBattery) {
        this.setId(referenceBattery.getId());
        this.setDuration(referenceBattery.getDuration());
        this.setEnergy(referenceBattery.getEnergy());
    }
}
