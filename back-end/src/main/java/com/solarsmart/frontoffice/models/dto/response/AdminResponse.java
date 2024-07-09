package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.Admin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminResponse extends UserResponse{
    private String name;
    private String lastName;

    public AdminResponse(Admin admin) {
        this.setEmail(admin.getEmail());
        this.setId(admin.getId());
        this.setName(admin.getName());
        this.setLastName(admin.getLastName());
    }
}
