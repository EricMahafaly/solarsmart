package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ModuleDetailResponse {
    private long id;
    private String reference;
    private LocalDateTime createdDate;
    private boolean used = false;

    private CustomerResponse customer;
    private BatteryInfoResponse battery;
    private PanelInfoResponse panel;
    private List<ModuleInfoResponse> othersInfo;

    public ModuleDetailResponse(ModuleSolar moduleSolar){
        this(moduleSolar, moduleSolar.getCustomer());
    }

    public ModuleDetailResponse(ModuleSolar moduleSolar, Customer customer) {
        if (customer != null){
            this.setUsed(true);
            this.setCustomer(new CustomerResponse(customer));
        }

        this.setId(moduleSolar.getId());
        this.setReference(moduleSolar.getReference());
        this.setCreatedDate(moduleSolar.getCreatedDate());
        this.setBattery(new BatteryInfoResponse(moduleSolar.getBattery()));
        this.setPanel(new PanelInfoResponse(moduleSolar.getPanel()));
        this.setOthersInfo(moduleSolar.getOtherInfo().stream()
                .map(ModuleInfoResponse::new)
                .collect(Collectors.toList()));

    }

    //    private
}
