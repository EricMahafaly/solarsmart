package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.services.api.base.BaseService;

import java.util.List;

public interface NotificationService extends BaseService<Notification> {

    List<Notification> getAllByModuleId(long moduleId);

     List<Notification> getAllNotReadByModuleId(Long moduleId);

     Notification markNotificationAsRead(Long notificationId);

    Notification create(long moduleId, Notification notification);

    Notification read(long id);
}
