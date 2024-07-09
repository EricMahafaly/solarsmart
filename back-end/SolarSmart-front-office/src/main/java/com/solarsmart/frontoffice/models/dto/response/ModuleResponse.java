package com.solarsmart.frontoffice.models.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleResponse {
    private long id;
    private String reference;
    private LocalDateTime createdDate;

    public ModuleResponse(ModuleSolar module){
        this.setId(module.getId());
        this.setReference(module.getReference());
        this.setCreatedDate(module.getCreatedDate());
    }

}
