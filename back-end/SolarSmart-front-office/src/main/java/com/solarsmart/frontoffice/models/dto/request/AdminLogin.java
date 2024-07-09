package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.models.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminLogin extends UserLogin {

    @Override
    public User toModel() {
        Admin admin = new Admin();
        admin.setEmail(this.getEmail());
        admin.setPassword(this.getPassword());
        return admin;
    }
}
