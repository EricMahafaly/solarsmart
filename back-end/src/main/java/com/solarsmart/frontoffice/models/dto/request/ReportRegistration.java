package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.Report}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRegistration implements Serializable {
    private String description;
    private Integer priority;
    private Long customerId;


    public Report toModel(){
        Report report = new Report();
        report.setDescription(this.getDescription());
        this.setPriority(priority);

        return report;
    }
}