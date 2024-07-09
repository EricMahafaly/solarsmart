package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.Notification;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NotificationResponse {
    private long id;
    private boolean isSeen;
    private String message;
    private LocalDateTime date;

    public NotificationResponse(Notification notification){
        if (notification == null) return;
        this.id = notification.getId();
        this.isSeen = notification.getSeen();
        this.message = notification.getMessage();
        this.date = notification.getDate();
    }

    public static List<NotificationResponse> toPublic(List<Notification> notifications){
        return notifications.stream()
                .map(NotificationResponse::new)
                .collect(Collectors.toList());
    }
}
