package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.models.exception.DomainException;
import lombok.Data;

import java.util.Objects;

@Data
public class AdminRegistration {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String confirmedPassword;

    public Admin toModel(){
        if (!this.allIsOk()) throw new DomainException("le mot de passe doit être identique");

        Admin admin = new Admin();
        admin.setName(this.getName());
        admin.setPassword(this.getPassword());
        admin.setEmail(this.getEmail());
        admin.setLastName(this.getLastName());

        return admin;
    }

    public boolean allIsOk(){
        return Objects.equals(this.getConfirmedPassword(), this.getPassword());
    }
}
