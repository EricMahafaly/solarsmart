package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import lombok.Data;

import java.util.List;

@Data
public class ModuleSolarStatistic {
    private int unused;
    private int used;
    private int total;

    public ModuleSolarStatistic(List<ModuleSolar> modulesSolar) {
        this.setTotal(modulesSolar.size());
        for (ModuleSolar moduleSolar : modulesSolar) {
            if (moduleSolar.getCustomer() != null) used++;
            else unused ++;
        }
    }
}
