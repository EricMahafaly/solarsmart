package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.RelaisStateInfo;
import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.PriseService;
import com.solarsmart.frontoffice.services.api.RelayPriseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solar/prises")
@AllArgsConstructor
@Authorize
//@CrossOrigin("*")
public class PriseController {
    private final PriseService priseService;
    private final RelayPriseService relayPriseService;

    @GetMapping("/info/modules/{moduleId}")
    public ResponseEntity<?> info(@PathVariable long moduleId){
        Prise prise = priseService.getByModuleId(moduleId);

        RelaisStateInfo batteryInfo = new RelaisStateInfo(prise.getRelaisState());

        ApiResponse<?> response = ApiResponse.success()
                .body(batteryInfo)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/relais/switch/modules/{moduleId}")
    public ResponseEntity<?> switchRelais(@PathVariable long moduleId){
        Prise prise = priseService.getByModuleId(moduleId);

        RelaisState relaisState = relayPriseService.switchRelais(prise);

        RelaisStateInfo relaisStateInfo = new RelaisStateInfo(relaisState);

        String message = relaisStateInfo.isActive() ? "The plug is active " : "The plug is turned off";

        ApiResponse<?> response = ApiResponse.success()
                .body(relaisStateInfo)
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }


}
