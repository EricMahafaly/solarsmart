package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public abstract class UserLogin {

    @Email
    @NotBlank(message = "email ne peut pas être vide")
    protected String email;

    @NotEmpty
    protected String password;

    public abstract User toModel();

//    protected String confirmedPassword;
}
