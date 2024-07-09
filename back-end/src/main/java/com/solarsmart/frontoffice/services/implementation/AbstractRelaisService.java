package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Composant;
import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.models.entities.RelayStateEnum;
import com.solarsmart.frontoffice.repositories.api.PlanningRepository;
import com.solarsmart.frontoffice.repositories.api.RelayRepository;
import com.solarsmart.frontoffice.services.api.NotificationService;
import com.solarsmart.frontoffice.services.api.SwitchableRelais;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;

//@AllArgsConstructor
@NoRepositoryBean
public abstract class AbstractRelaisService<T extends Composant> extends AbstractSwitchableRelais<T> implements SwitchableRelais<T> {

    protected final PlanningRepository planningRepository;

    protected final NotificationService notificationService;

    public AbstractRelaisService(RelayRepository relayRepository,
                                 PlanningRepository planningRepository,
                                 NotificationService notificationService) {

        super(relayRepository);
        this.planningRepository = planningRepository;
        this.notificationService = notificationService;
    }

    protected abstract Notification createNotification(LocalDateTime now);

    private T relayed(T composant, RelaisState relaisState){
        if (composant.getRelaisState().equals(relaisState) || relaisState == null) return composant;
        composant.setRelaisState(relaisState);
        return composant;
    }

    protected long getModuleId(T composant){
        return composant.getModule().getId();
    }

    protected abstract void markAllPlanningDoneByCondition(long moduleId, LocalDateTime now, double courant);

    protected abstract void saveComposant(T composant);

    protected RelaisState getStateByPlanningAndMarkPlanningDoneIfExist(long moduleId, LocalDateTime now, double courant){
        if (planningRepository.countAllNotDone(moduleId) == 0) return null;
        long count1 = this.planningRepository.countAllNotDoneDateBetweenStartAndEnd(moduleId, now, courant);

        if (count1 > 0){
            RelaisState relaisState = this.relayRepository.findByState(RelayStateEnum.HIGH);

            Notification notification = createNotification(now);
            this.notificationService.create(moduleId, notification);

            return relaisState;
        }

        long count2 = this.planningRepository.countAllNotDoneAndDateAfterEnd(moduleId, now, courant);

        this.markAllPlanningDoneByCondition(moduleId, now, courant);

        return this.relayRepository.findByState(RelayStateEnum.LOW);
    }



    public void relayed(T composant, double courant) {

        LocalDateTime now = LocalDateTime.now();
        RelaisState relaisState = this.getStateByPlanningAndMarkPlanningDoneIfExist(this.getModuleId(composant), now, courant);
        T priseRelayed = this.relayed(composant, relaisState);
    }
}
