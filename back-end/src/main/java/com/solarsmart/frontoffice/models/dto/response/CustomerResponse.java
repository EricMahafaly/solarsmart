package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerResponse extends UserResponse{
    private String name;
    private String lastName;
    private String image;
    private String postalCode;
    private String address;
//    private String moduleName;

    public CustomerResponse(Customer customer){
        if (customer != null){
            this.setId(customer.getId());
            this.setName(customer.getName());
            this.setLastName(customer.getLastName());
            this.setEmail(customer.getEmail());
            this.setImage(customer.getImage());
            this.setPostalCode(customer.getPostalCode());
            this.setAddress(customer.getAddress());
        }

    }
}
