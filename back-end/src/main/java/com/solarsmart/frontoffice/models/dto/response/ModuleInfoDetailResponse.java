package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfoDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ModuleAdditionalInfoDetail}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleInfoDetailResponse implements Serializable {
    private Long id;
    private String key;
    private String value;
    private String description;

    public ModuleInfoDetailResponse(ModuleAdditionalInfoDetail moduleAdditionalInfoDetail) {
        this.setId(moduleAdditionalInfoDetail.getId());
        this.setKey(moduleAdditionalInfoDetail.getKey());
        this.setValue(moduleAdditionalInfoDetail.getValue());
        this.setDescription(moduleAdditionalInfoDetail.getDescription());
    }
}