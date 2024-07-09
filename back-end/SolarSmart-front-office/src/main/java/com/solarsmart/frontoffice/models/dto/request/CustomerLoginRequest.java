package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerLoginRequest extends UserLogin {

    @Override
    public Customer toModel(){
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);

        return customer;
    }
}
