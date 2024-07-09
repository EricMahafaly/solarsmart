package com.solarsmart.frontoffice.models.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solarsmart.frontoffice.models.entities.ReferenceBattery;
import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import com.solarsmart.frontoffice.services.helpers.DateHelper;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ReferenceDurationRequest {
    private double duration;

    @JsonIgnore
    public LocalTime getDurationTime(){
        return DateHelper.toLocalTime(duration);
    }

    public ReferenceBattery toReferenceBattery(){
        ReferenceBattery referenceBattery = new ReferenceBattery();

        referenceBattery.setDuration(DateHelper.toLocalTime(duration));

        return referenceBattery;
    }

    public ReferencePrise toReferencePrise(){
        ReferencePrise referencePrise = new ReferencePrise();

        referencePrise.setDuration(DateHelper.toLocalTime(duration));

        return referencePrise;
    }
}
