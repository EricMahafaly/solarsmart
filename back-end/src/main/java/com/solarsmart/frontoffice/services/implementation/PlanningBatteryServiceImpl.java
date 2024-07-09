package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.models.entities.PlanningBattery;
import com.solarsmart.frontoffice.repositories.api.PlanningBatteryRepository;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.api.NotificationService;
import com.solarsmart.frontoffice.services.api.PlanningBatteryService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PlanningBatteryServiceImpl extends BaseServiceImpl<PlanningBattery, PlanningBatteryRepository> implements PlanningBatteryService {
    private final ModuleService moduleService;

    private final NotificationService notificationService;
    public PlanningBatteryServiceImpl(PlanningBatteryRepository repository, ModuleService moduleService, NotificationService notificationService) {
        super(repository);
        this.moduleService = moduleService;
        this.notificationService = notificationService;
    }

    @Override
    public void markAllPlanningDoneByCondition(long moduleId, LocalDateTime time, double courant) {
        this.repository.markAllDoneByCondition(moduleId, time, courant);
        this.notifying(time, moduleId);
    }

    private void notifying(LocalDateTime time, long moduleId){
        ModuleSolar moduleSolar = this.moduleService.get(moduleId);

        Notification notification = new Notification();
        notification.setSeen(false);
        notification.setDate(time);
        notification.setModule(moduleSolar);
        notification.setMessage("Le temps d’utilisation planifié sur votre prise s’étais écoulé a "+ time);

        notificationService.create(notification);
    }
}
