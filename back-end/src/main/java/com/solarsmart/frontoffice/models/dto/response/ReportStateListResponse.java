package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.ReportState;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.ReportState}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportStateListResponse implements Serializable {
    private Long id;
    private String state;
    private Integer value;

    public ReportStateListResponse(ReportState reportState){
        this.setId(reportState.getId());
        this.setState(reportState.getState().getValue());
        this.setValue(reportState.getValue());
    }
}