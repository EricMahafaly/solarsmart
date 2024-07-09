package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfo;
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
public class ModuleInfoResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private List<ModuleInfoDetailResponse> details;


    public ModuleInfoResponse(ModuleAdditionalInfo moduleAdditionalInfo) {
        this.setId(moduleAdditionalInfo.getId());
        this.setName(moduleAdditionalInfo.getName());
        this.setDescription(moduleAdditionalInfo.getDescription());
        this.setDetails(moduleAdditionalInfo.getDetails()
                .stream()
                .map(ModuleInfoDetailResponse::new)
                .collect(Collectors.toList()));
    }
}