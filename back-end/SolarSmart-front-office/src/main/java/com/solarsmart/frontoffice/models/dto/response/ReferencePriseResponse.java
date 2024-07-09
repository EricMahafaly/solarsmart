package com.solarsmart.frontoffice.models.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ReferencePriseResponse {
    private long id;

    private LocalTime duration;

    private Double consommation;

    public ReferencePriseResponse(ReferencePrise referencePrise) {
        this.setId(referencePrise.getId());
        this.setDuration(referencePrise.getDuration());
        this.setConsommation(referencePrise.getConsommation());
    }
}
