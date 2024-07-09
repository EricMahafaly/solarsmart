package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Customer;
import lombok.Data;

@Data
public class CustomerRegistration {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String postalCode;
    private String image;
    private String address;

    public Customer toModel(){
        Customer customer = new Customer();
        customer.setName(this.getName());
        customer.setLastName(this.getLastName());
        customer.setEmail(this.getEmail());
        customer.setPassword(this.getPassword());
        customer.setPostalCode(this.getPostalCode());
        customer.setImage(this.getImage());
        customer.setAddress(this.getAddress());

        return customer;
    }
}