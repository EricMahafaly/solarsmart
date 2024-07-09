package com.solarsmart.frontoffice.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QrCodeModel {
    @JsonProperty("id")
    private long moduleId;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("created_date")
    private String createdDate;

    public QrCodeModel(ModuleSolar moduleSolar){
        this.moduleId = moduleSolar.getId();
        this.setReference(moduleSolar.getReference());
        this.createdDate = moduleSolar.getCreatedDate().toString();
    }

}
