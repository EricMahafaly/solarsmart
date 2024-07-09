package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse<U extends UserResponse> {
    private String token = "";
    private U user;
//    private
}
