package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.BatteryDataResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/batteryData")
    @SendTo("/topic/batteryData")
    public BatteryDataResponse sendBatteryData(BatteryDataResponse data) {
        return data;
    }
}