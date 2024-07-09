package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.repositories.api.NotificationRepository;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.api.NotificationService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl extends BaseServiceImpl<Notification, NotificationRepository> implements NotificationService {

    private final ModuleService moduleService;
    public NotificationServiceImpl(NotificationRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public List<Notification> getAllByModuleId(long moduleId) {
        this.moduleService.get(moduleId);
        return repository.getAllByModuleIdDateDesc(moduleId);
    }

    @Override
    public List<Notification> getAllNotReadByModuleId(Long moduleId) {
        return null;
    }

    @Override
    public Notification markNotificationAsRead(Long notificationId) {

        Notification notification = this.get(notificationId);

        return repository.save(notification);
    }

    @Override
    public Notification create(long moduleId, Notification notification) {

        ModuleSolar moduleSolar = this.moduleService.get(moduleId);

        notification.setModule(moduleSolar);
        return this.repository.save(notification);
    }

    @Override
    public Notification read(long id) {
        Notification notification = this.repository.findById(id).orElse(null);
        if (notification != null) notification.setSeen(true);

        return repository.save(notification);
    }
}
