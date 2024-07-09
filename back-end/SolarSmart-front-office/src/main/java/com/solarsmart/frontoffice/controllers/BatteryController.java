package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.*;
import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.models.entities.RelayStateEnum;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.BatteryService;
import com.solarsmart.frontoffice.services.api.RelaisService;
import com.solarsmart.frontoffice.services.api.RelayBatterieService;
import com.solarsmart.frontoffice.services.api.StatisticsBatteryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solar/batteries")
@AllArgsConstructor
//@Authorize
//@CrossOrigin("*")
public class BatteryController {
    private final BatteryService batteryService;
    private final RelayBatterieService relayBatterieService;
    private final StatisticsBatteryService statisticsBatteryService;
    private final RelaisService relaisService;

    @GetMapping("/distinct")
    @Authorize
    public ResponseEntity<?> getAllDistinct(){
        List<BatteryDistinctResponse> data = this.batteryService.getAllDistinct()
                .stream().map(BatteryDistinctResponse::new)
                .toList();

        ApiResponse<?> response = ApiResponse.success()
                .body(data)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/modules/{moduleId}")
    @Authorize
    public ResponseEntity<?> getAllByModule(@PathVariable long moduleId){
        return ResponseEntity.ok().build();
    }

    /***
     * Prendre les infos du battery par module (état du relais et info de base de la batterie)
     * @param moduleId
     * @return Info
     */
    @GetMapping("/info/modules/{moduleId}")
    @Authorize
    public ResponseEntity<?> info(@PathVariable long moduleId){
        Battery battery = batteryService.getByModule(moduleId);

        BatteryInfoResponse batteryInfo = new BatteryInfoResponse(battery);

        ApiResponse<?> response = ApiResponse.success()
                .body(batteryInfo)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/relais/modules/{moduleId}")
    public ResponseEntity<?> getRelais(@PathVariable long moduleId){
        Battery battery = batteryService.getByModule(moduleId);

        RelaisStateInfo relaisStateInfo = new RelaisStateInfo(battery.getRelaisState());

        return ResponseEntity.ok(relaisStateInfo);
    }

    @GetMapping("/relais/switch/modules/{moduleId}")
    public ResponseEntity<?> switchRelais(@PathVariable long moduleId){
        Battery battery = batteryService.getByModule(moduleId);

        RelaisState relaisState = relayBatterieService.switchRelais(battery);

        RelaisStateInfo relaisStateInfo = new RelaisStateInfo(relaisState);

        String message = relaisStateInfo.isActive() ? "battery is on " : "battery is off";

        ApiResponse<?> response = ApiResponse.success()
                .body(relaisStateInfo)
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/relais/off/modules/{moduleId}")
    public ResponseEntity<?> relaisOff(@PathVariable long moduleId){
        Battery battery = batteryService.getByModule(moduleId);

        RelaisState relaisState = relaisService.getByState(RelayStateEnum.HIGH);

        battery.setRelaisState(relaisState);

        batteryService.create(battery);

        RelaisStateInfo relaisStateInfo = new RelaisStateInfo(battery.getRelaisState());


        return ResponseEntity.ok(relaisStateInfo);
    }

    @GetMapping("/relais/on/modules/{moduleId}")
    public ResponseEntity<?> relaisOn(@PathVariable long moduleId){
        Battery battery = batteryService.getByModule(moduleId);

        RelaisState relaisState = relaisService.getByState(RelayStateEnum.LOW);

        battery.setRelaisState(relaisState);

        batteryService.create(battery);

        RelaisStateInfo relaisStateInfo = new RelaisStateInfo(battery.getRelaisState());

        return ResponseEntity.ok(relaisStateInfo);
    }


    @GetMapping("/detail/percentage/modules/{moduleId}")
    @Authorize
    public ResponseEntity<?> getCurrentPercentage(@PathVariable long moduleId){
        ApiResponse<?> response = ApiResponse.success()
                .body(statisticsBatteryService.getCurrentPercentageByModuleId(moduleId))
                .message("percentage received successfully")
                .build();

        return ResponseEntity.ok(response);

    }

}
