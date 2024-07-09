package com.solarsmart.frontoffice.models.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class BatteryInfoResponse {
    private long id;
    private double voltage;
    private String marque;
    private double puissance;

    @JsonProperty("relais_state")
    private RelaisStateInfo relaisState;

    public BatteryInfoResponse(Battery battery) {
//        this(new RelaisStateInfo(battery.getRelaisState()), battery);
        RelaisState relaisState = battery.getRelaisState();
        this.relaisState = new RelaisStateInfo(relaisState);
        this.voltage = battery.getVoltage();
        this.marque = battery.getMarque();
        this.puissance = battery.getPuissance();
    }
}
