package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfo;
import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfoDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link ModuleAdditionalInfo}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleAdditionalInfoRegistration implements Serializable {

    private Long id;
    private String description;
    private String name;
    private List<ModuleAdditionalInfoDetailRegistration> details;


    public ModuleAdditionalInfo toModel(){
        ModuleAdditionalInfo moduleAdditionalInfo = new ModuleAdditionalInfo();
        moduleAdditionalInfo.setId(this.getId());
        moduleAdditionalInfo.setDescription(this.getDescription());
        moduleAdditionalInfo.setName(this.getName());

        List<ModuleAdditionalInfoDetail> moduleAdditionalInfoDetails = ModuleAdditionalInfoDetailRegistration.toModel(this.getDetails());
        moduleAdditionalInfoDetails.forEach(moduleAdditionalInfoDetail -> moduleAdditionalInfoDetail.setModuleAdditionalInfo(moduleAdditionalInfo));

        moduleAdditionalInfo.setDetails(moduleAdditionalInfoDetails);

        return moduleAdditionalInfo;
    }

    public static List<ModuleAdditionalInfo> toModel(List<ModuleAdditionalInfoRegistration> moduleAdditionalInfoRegistrations){
        return moduleAdditionalInfoRegistrations.stream()
                .map(moduleAdditionalInfoRegistration -> moduleAdditionalInfoRegistration.toModel())
                .collect(Collectors.toList());
    }
}