package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.repositories.api.PlanningPriseRepository;
import com.solarsmart.frontoffice.repositories.api.PriseRepository;
import com.solarsmart.frontoffice.repositories.api.RelayRepository;
import com.solarsmart.frontoffice.services.api.NotificationService;
import com.solarsmart.frontoffice.services.api.PlanningPriseService;
import com.solarsmart.frontoffice.services.api.RelayPriseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RelaisPriseServiceImpl extends AbstractRelaisService<Prise> implements RelayPriseService {
    private final PlanningPriseService planningPriseService;
    private final PriseRepository priseRepository;
    public RelaisPriseServiceImpl(PlanningPriseRepository planningRepository,
                                  NotificationService notificationService,
                                  RelayRepository relayRepository,
                                  PlanningPriseService planningPriseService,
                                  PriseRepository priseRepository) {

        super(relayRepository, planningRepository, notificationService);
        this.planningPriseService = planningPriseService;
        this.priseRepository = priseRepository;

    }

    @Override
    protected Notification createNotification(LocalDateTime now) {
        Notification notification = new Notification();
        notification.setDate(now);
        notification.setSeen(false);
        notification.setMessage("Le temps de planification sur la prise commence  a "+now);

        return notification;
    }

    @Override
    protected void markAllPlanningDoneByCondition(long moduleId, LocalDateTime now, double courant) {
//        this.planningProcessService.markAllPlanningPriseDoneByCondition(moduleId, now, courant);
        this.planningPriseService.markAllPlanningDoneByCondition(moduleId, now, courant);
    }

    @Override
    protected void saveComposant(Prise composant) {
        this.priseRepository.save(composant);
    }

}
