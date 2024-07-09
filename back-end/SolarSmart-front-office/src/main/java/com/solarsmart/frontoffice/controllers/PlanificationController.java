package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.request.PlanningRegistration;
import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.models.entities.PlanningBattery;
import com.solarsmart.frontoffice.models.entities.PlanningPrise;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.BatteryService;
import com.solarsmart.frontoffice.services.api.PlanningBatteryService;
import com.solarsmart.frontoffice.services.api.PlanningPriseService;
import com.solarsmart.frontoffice.services.api.PriseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solar/planning")
@AllArgsConstructor
@Authorize
public class PlanificationController {
    private final PlanningBatteryService planningBatteryService;
    private final PlanningPriseService planningPriseService;

    private final BatteryService batteryService;
    private final PriseService priseService;
    @PostMapping("/batteries/modules/{idModule}")
    public ResponseEntity<?> planBattery(@RequestBody PlanningRegistration planningRegistration,
                                         @PathVariable long idModule){
        Battery battery = batteryService.getByModule(idModule);

        PlanningBattery planningBattery = planningRegistration.toPlanningBatteryModel();
        planningBattery.setBattery(battery);

        ApiResponse<?> response = ApiResponse.success()
                .message("Battery planning is a success")
                .body(planningBatteryService.create(planningBattery))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/prises/modules/{idModule}")
    public ResponseEntity<?> planPrise(@RequestBody PlanningRegistration planningRegistration,
                                         @PathVariable long idModule){
        Prise prise = priseService.getByModuleId(idModule);

        PlanningPrise planningPrise = planningRegistration.toPlanningPriseModel();
        planningPrise.setPrise(prise);

        ApiResponse<?> response = ApiResponse.success()
                .message("Battery planning is a success")
                .body(planningPriseService.create(planningPrise))
                .build();

        return ResponseEntity.ok(response);
    }
}
