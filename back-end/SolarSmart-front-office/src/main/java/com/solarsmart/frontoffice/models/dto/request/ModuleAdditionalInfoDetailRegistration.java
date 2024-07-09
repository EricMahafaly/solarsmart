package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfoDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link ModuleAdditionalInfoDetail}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleAdditionalInfoDetailRegistration implements Serializable {
    private Long id;
    private String key;
    private String value;
    private String description;

    public ModuleAdditionalInfoDetail toModel(){
        ModuleAdditionalInfoDetail moduleAdditionalInfoDetail = new ModuleAdditionalInfoDetail();
        moduleAdditionalInfoDetail.setId(this.getId());
        moduleAdditionalInfoDetail.setKey(this.getKey());
        moduleAdditionalInfoDetail.setValue(this.getValue());
        moduleAdditionalInfoDetail.setDescription(this.getDescription());

        return moduleAdditionalInfoDetail;
    }

    public static List<ModuleAdditionalInfoDetail> toModel(List<ModuleAdditionalInfoDetailRegistration> moduleAdditionalInfoDetailRegistrations){
        return moduleAdditionalInfoDetailRegistrations.stream()
                .map(moduleInfoRegistration -> moduleInfoRegistration.toModel())
                .collect(Collectors.toList());
    }
}