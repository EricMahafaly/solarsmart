package com.solarsmart.frontoffice.controllers;


import com.solarsmart.frontoffice.models.dto.response.*;
import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.PanelService;
import com.solarsmart.frontoffice.services.api.RelayPanelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solar/panels")
@AllArgsConstructor
//@CrossOrigin("*")
@Slf4j
@Authorize
public class PanelController {
    private final PanelService panelService;
    private final RelayPanelService relayPanelService;

    @GetMapping("/distinct")
    public ResponseEntity<?> getAllDistinct(){
        List<PanelDistinctResponse> data = this.panelService.getAllDistinct()
                .stream().map(PanelDistinctResponse::new)
                .toList();

        ApiResponse<?> response = ApiResponse.success()
                .body(data)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/info/modules/{moduleId}")
    public ResponseEntity<?> info(@PathVariable long moduleId) {
        Panel panel = panelService.getByModuleId(moduleId);
        PanelInfoResponse panelInfo = new PanelInfoResponse(panel);

        ApiResponse<?> response = ApiResponse.success()
                .body(panelInfo)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/relais/switch/modules/{moduleId}")
    public ResponseEntity<?> switchRelais(@PathVariable long moduleId){
        Panel panel = panelService.getByModuleId(moduleId);

        log.info("relais state = {}", panel.getRelaisState());

        RelaisState relaisState = relayPanelService.switchRelais(panel);

        log.info("new relais state = {}", relaisState);

        RelaisStateInfo relaisStateInfo = new RelaisStateInfo(relaisState);

        String message = relaisStateInfo.isActive() ? "The panel is activated " : "The panel is off";

        ApiResponse<?> response = ApiResponse.success()
                .body(relaisStateInfo)
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }
}
