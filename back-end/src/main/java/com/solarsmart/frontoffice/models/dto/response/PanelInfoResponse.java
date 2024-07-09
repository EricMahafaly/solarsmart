package com.solarsmart.frontoffice.models.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@AllArgsConstructor
public class PanelInfoResponse {
    private long id;
    private double voltage;
    private String marque;
    private double puissance;

    @JsonProperty("relais_state")
    private RelaisStateInfo relaisState;

    public PanelInfoResponse(Panel panel) {

        RelaisState relaisState = panel.getRelaisState();
        this.relaisState = new RelaisStateInfo(relaisState);
        this.voltage = panel.getVoltage();
        this.marque = panel.getMarque();
        this.puissance = panel.getPuissance();
    }
}
