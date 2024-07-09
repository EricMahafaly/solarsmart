package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.request.AdminLogin;
import com.solarsmart.frontoffice.models.dto.request.AdminRegistration;
import com.solarsmart.frontoffice.models.dto.request.CustomerRegistration;
import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.AuthenticationResponse;
import com.solarsmart.frontoffice.models.dto.response.CustomerResponse;
import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.security.TokenService;
import com.solarsmart.frontoffice.services.api.CustomerService;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.security.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/solar/auth")
@RequiredArgsConstructor
@Slf4j
//@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    @PostMapping("/admin/signin")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminLogin loginModel){
        AuthenticationResponse<?> auth = authenticationService.signIn((Admin) loginModel.toModel());

        ApiResponse<?> response = ApiResponse.builder()
                .isSuccess(true)
                .body(auth)
                .message("Authentication is successful")
                .build();

        log.info("token: {}", auth.getToken());

        return ResponseEntity.ok(response);
    }



    @PostMapping("/admin/signup")
    public ResponseEntity<?> signUpAdmin(@RequestBody AdminRegistration adminSignUp){

        AuthenticationResponse<?> auth = authenticationService.signUp(adminSignUp.toModel());

        ApiResponse<?> response = ApiResponse.builder()
                .isSuccess(true)
                .body(auth)
//                .message("la creation de l'utilisateur est un succès")
                .message("User creation is successful")
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/logout")
    public ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String authorization){
        String token = this.tokenService.getTokenFromHeader(authorization);

        this.authenticationService.logout(token);

        ApiResponse<?> response = ApiResponse.builder()
                .isSuccess(true)
                .message("You are disconnected")
                .build();

        return ResponseEntity.ok(response);
    }
}
