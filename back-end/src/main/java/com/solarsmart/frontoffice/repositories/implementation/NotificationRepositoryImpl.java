package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.repositories.api.NotificationRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaNotificationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepositoryImpl extends BaseRepositoryImpl<Notification, JpaNotificationRepository> implements NotificationRepository {
    public NotificationRepositoryImpl(JpaNotificationRepository repository) {
        super(repository);
    }

    @Override
    public List<Notification> getAllByModuleIdDateDesc(long moduleId) {
        return repository.findByModule_IdOrderByDate(moduleId);
    }

    @Override
    public List<Notification> getAllNotReadByModuleIdDateDesc(long moduleId) {
        return repository.findByModule_IdAndSeenFalseOrderByDate(moduleId);
    }
}
