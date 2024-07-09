package com.solarsmart.frontoffice.security;

import com.solarsmart.frontoffice.models.dto.response.AuthenticationResponse;
import com.solarsmart.frontoffice.models.entities.Admin;

public interface AuthenticationService {
    AuthenticationResponse<?> signIn(Admin admin);

    AuthenticationResponse<?> signUp(Admin admin);

    void logout(String token);

}
