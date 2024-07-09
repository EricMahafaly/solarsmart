package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfo;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.Panel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.ModuleSolar}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleSolarRequest implements Serializable {
    private BatteryRequest battery;
    private PanelRequest panel;
    private List<ModuleAdditionalInfoRegistration> othersInfo;

    private String generateReference(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datePart = LocalDate.now().format(formatter);
        String uniquePart = String.format("%04d", ThreadLocalRandom.current().nextInt(10000));
        return "MOD-" + datePart + "-" + uniquePart;
    }

    public List<ModuleAdditionalInfo> extractOtherInfo(){
        if (this.othersInfo == null) return new ArrayList<>();
        List<ModuleAdditionalInfo> moduleAdditionalInfos = ModuleAdditionalInfoRegistration.toModel(this.getOthersInfo());
//        moduleAdditionalInfos.forEach(moduleInfo -> moduleInfo.setModule(this.extractModuleSolar()));
        return moduleAdditionalInfos;
    }

    public ModuleSolar extractModuleSolar(){
        ModuleSolar moduleSolar = new ModuleSolar();
        moduleSolar.setReference(this.generateReference());

        return moduleSolar;
    }

    public Panel extractPanel(){
        return this.panel.toModel();
    }

    public Battery extractBattery(){
        return battery.toModel();
    }
}