package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.Subscription;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CustomerDetailResponse extends CustomerResponse{
    private long moduleId;
    private String moduleReference;
    private String subscription;

    public CustomerDetailResponse(Customer customer, Subscription subscription, ModuleSolar module) {
        super(customer);
        if (module != null) {
            this.setModuleId(module.getId());
            this.setModuleReference(module.getReference());
        }

        if (subscription != null) this.setSubscription(subscription.getName());
    }
}
