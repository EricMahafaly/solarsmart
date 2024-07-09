package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Panel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.Panel}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanelRequest implements Serializable {

    private Long id;
    private String marque;
    private Double puissance;
    private Double voltage;


    Panel toModel(){
        Panel panel = new Panel();
        panel.setId(this.getId());
        panel.setMarque(this.getMarque());
        panel.setPuissance(this.getPuissance());
        panel.setVoltage(this.getVoltage());

        return panel;
    }
}