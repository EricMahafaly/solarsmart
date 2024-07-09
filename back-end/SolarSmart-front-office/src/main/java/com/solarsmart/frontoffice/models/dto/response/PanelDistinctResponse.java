package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.projection.PanelDistinct;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.Panel}
 */
@Value
@Data
public class PanelDistinctResponse implements Serializable {
    String marque;
    Double puissance;
    Double voltage;

    public PanelDistinctResponse(PanelDistinct panel) {
        this.marque = panel.getMarque();
        this.puissance = panel.getPuissance();
        this.voltage = panel.getVoltage();
    }
}