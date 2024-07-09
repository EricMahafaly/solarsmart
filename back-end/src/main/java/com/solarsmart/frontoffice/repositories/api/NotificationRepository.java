package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.util.List;


public interface NotificationRepository extends BaseRepository<Notification> {
    List<Notification> getAllByModuleIdDateDesc(long moduleId);
    List<Notification> getAllNotReadByModuleIdDateDesc(long moduleId);
}
