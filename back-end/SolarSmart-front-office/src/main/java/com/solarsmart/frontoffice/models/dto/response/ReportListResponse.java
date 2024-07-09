package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.Report;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.Report}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportListResponse implements Serializable {
    private Long id;
    private LocalDateTime createdDate;
    private String description;
    private Long customerId;
    private String customerName;
    private String customerImage;
    private ReportStateEnum state;

    public ReportListResponse (Report report){
        Customer customer = report.getCustomer();

        this.setId(report.getId());
        this.setCreatedDate(report.getCreatedDate());
        this.setDescription(report.getDescription());

        this.setCustomerInfo(customer);

        this.setState(report.getState().getState());

    }

    private void setCustomerInfo(Customer customer){
        this.setCustomerId(customer.getId());
        this.setCustomerImage(customer.getImage());
        this.setCustomerName(customer.getName() + " " + customer.getLastName());
    }
}