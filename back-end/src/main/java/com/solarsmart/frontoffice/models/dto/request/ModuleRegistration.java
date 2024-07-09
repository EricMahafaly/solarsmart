package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.Panel;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class ModuleRegistration {
    private BatteryRegistration battery;
    private PanelRegistration panel;

    public ModuleSolar extractModel(){
        ModuleSolar moduleSolar = new ModuleSolar();
        moduleSolar.setReference(this.generateReference());

        return moduleSolar;
    }

    public String generateReference(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datePart = LocalDate.now().format(formatter);
        String uniquePart = String.format("%04d", ThreadLocalRandom.current().nextInt(10000));
        return "MOD-" + datePart + "-" + uniquePart;
    }

    public Panel extractPanel(){
        return panel.toModel();
    }

    public Battery extractBattery(){
        return battery.toModel();
    }
}
