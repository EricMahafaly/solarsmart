package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.NotificationResponse;
import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solar/notifications")
@AllArgsConstructor
@Authorize
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<?> list(){
        List<Notification> notifications = this.notificationService.list();
        ApiResponse<?> response = ApiResponse.success()
                .body(NotificationResponse.toPublic(notifications))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/modules/{id}")
    public ResponseEntity<?> listByModuleId(@PathVariable(name = "id") long moduleId){
        List<Notification> notifications = this.notificationService.getAllByModuleId(moduleId);

        ApiResponse<?> response = ApiResponse.success()
                .body(NotificationResponse.toPublic(notifications))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable long id){
        Notification notification = this.notificationService.get(id);

        ApiResponse<?> response = ApiResponse.success()
                .body(new NotificationResponse(notification))
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}/read")
    public ResponseEntity<?> readNotification(@PathVariable long id){
        Notification notification = notificationService.markNotificationAsRead(id);

        ApiResponse<?> response = ApiResponse.success()
                .body(new NotificationResponse(notification))
                .build();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/not-read/modules/{id}")
    public ResponseEntity<?> getNotificationNotRead(@PathVariable long id){
        List<Notification> notifications = notificationService.getAllNotReadByModuleId(id);

        ApiResponse<?> response = ApiResponse.success()
                .body(NotificationResponse.toPublic(notifications))
                .build();

        return ResponseEntity.ok(response);
    }
}
