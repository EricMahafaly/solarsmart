package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Battery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.Battery}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryRequest implements Serializable {
    private Long id;
    private String marque;
    private Double puissance;
    private Double voltage;


    public Battery toModel() {
        Battery battery = new Battery();
        battery.setId(this.getId());
        battery.setMarque(this.getMarque());
        battery.setPuissance(this.getPuissance());
        battery.setVoltage(this.getVoltage());

        return battery;
    }
}