package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.repositories.api.BatteryRepository;
import com.solarsmart.frontoffice.repositories.api.RelayRepository;
import com.solarsmart.frontoffice.repositories.api.PlanningBatteryRepository;
import com.solarsmart.frontoffice.services.api.NotificationService;
import com.solarsmart.frontoffice.services.api.PlanningBatteryService;
import com.solarsmart.frontoffice.services.api.RelayBatterieService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RelaisBatteryServiceImpl extends AbstractRelaisService<Battery> implements RelayBatterieService {
    private final BatteryRepository batteryRepository;
    private final PlanningBatteryService planningBatteryService;
    public RelaisBatteryServiceImpl(PlanningBatteryRepository planningRepository, NotificationService notificationService,
                                    RelayRepository relayRepository, BatteryRepository batteryRepository,
                                    PlanningBatteryService planningBatteryService
                                    ) {
        super(relayRepository, planningRepository, notificationService);
        this.batteryRepository = batteryRepository;
        this.planningBatteryService = planningBatteryService;
    }

    @Override
    protected Notification createNotification(LocalDateTime now) {
        Notification notification = new Notification();
        notification.setDate(LocalDateTime.now());
        notification.setSeen(false);
        notification.setMessage("Le temps de planification sur la batterie commence  a "+now);

        return notification;
    }

    @Override
    protected void markAllPlanningDoneByCondition(long moduleId, LocalDateTime now, double courant) {
        this.planningBatteryService.markAllPlanningDoneByCondition(moduleId, now, courant);
    }

    @Override
    protected void saveComposant(Battery composant) {
        this.batteryRepository.save(composant);
    }
}
