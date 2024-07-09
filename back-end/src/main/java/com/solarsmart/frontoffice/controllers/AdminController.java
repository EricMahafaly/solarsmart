package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.AdminResponse;
import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.security.TokenService;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solar/admins")
@RequiredArgsConstructor
@Slf4j
@Authorize
public class AdminController {
    private final AdminService adminService;
    private final TokenService tokenService;

    @GetMapping("/details")
    public ResponseEntity<?> getDetails(@RequestHeader(name = "Authorization")String authorization){
        String token = this.tokenService.getTokenFromHeader(authorization);

        long id = Long.parseLong(this.tokenService.extractId(token));
        Admin admin = adminService.get(id);

        log.info("get admin detail by id {}: {}", id, admin);

        ApiResponse<?> response = ApiResponse
                .builder()
                .body(new AdminResponse(admin))
                .isSuccess(true)
                .build();

        return ResponseEntity.ok(response);
    }

}
