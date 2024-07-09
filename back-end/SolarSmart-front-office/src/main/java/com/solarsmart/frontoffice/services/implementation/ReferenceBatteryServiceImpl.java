package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.models.entities.ReferenceBattery;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.models.views.TimeUsageDaily;
import com.solarsmart.frontoffice.repositories.api.BatteryRepository;
import com.solarsmart.frontoffice.repositories.api.BatteryTimeUsageRepository;
import com.solarsmart.frontoffice.repositories.api.ReferenceBatteryRepository;
import com.solarsmart.frontoffice.services.api.NotificationService;
import com.solarsmart.frontoffice.services.api.ReferenceBatteryService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReferenceBatteryServiceImpl extends BaseServiceImpl<ReferenceBattery, ReferenceBatteryRepository>
        implements ReferenceBatteryService {
    private final BatteryRepository batteryRepository;
    private final NotificationService notificationService;
    private final BatteryTimeUsageRepository batteryTimeUsageRepository;
//    private final
    public ReferenceBatteryServiceImpl(ReferenceBatteryRepository repository, BatteryRepository batteryRepository, NotificationService notificationService, BatteryTimeUsageRepository batteryTimeUsageRepository) {
        super(repository);
        this.batteryRepository = batteryRepository;
        this.notificationService = notificationService;
        this.batteryTimeUsageRepository = batteryTimeUsageRepository;
    }

    @Override
    public ReferenceBattery toDefault(Long moduleId) {
        ReferenceBattery referencePrise = this.findByModuleId(moduleId);

        ReferenceBattery newRef = new ReferenceBattery();

        newRef.setModule(referencePrise.getModule());
        newRef.setId(referencePrise.getId());

        return repository.save(newRef);
    }

    @Override
    public ReferenceBattery findByModuleId(Long moduleId) {

//        ModuleSolar moduleSolar = moduleService.get(moduleId);
        return repository.findByModuleId(moduleId)
                .orElseThrow(()-> new DomainException("cette module ne possède aucune reference battery"));
    }

    @Override
    public ReferenceBattery handleIfConditionTrue(Long moduleId, LocalDateTime date) {

        ReferenceBattery referenceNotCheck = repository.findNotCheckedByModuleAndDate(moduleId, date.toLocalDate())
                .orElse(null);

        if (referenceNotCheck != null){
            TimeUsageDaily batteryTimeUsageDaily = batteryTimeUsageRepository
                    .getTimeUsageByModuleAndDate(moduleId, date.toLocalDate());

            double duration = batteryTimeUsageDaily.getDuration();

            double energy = this.getEnergy(moduleId, date);

            String state = referenceNotCheck.getSate();

            if (!state.contains("1") && duration <= referenceNotCheck.getDurationInSecond()){
                state += 1;
                Notification notification = new Notification();
                notification.setMessage("la durée d'utilisation de la batterie a atteint la limite du reference à "+ date);
                notification.setDate(date);

                notificationService.create(moduleId, notification);
            }

            if (!state.contains("2") && energy <= referenceNotCheck.getEnergy()){
                state += 2;
                Notification notification = new Notification();
                notification.setMessage("l'énergie de la batterie a atteint a atteint la limite du reference à "+ date);
                notification.setDate(date);

                notificationService.create(moduleId, notification);
            }

//            setReferenceState(referenceNotCheck, state, date);

            referenceNotCheck.setSate(date);

//            referenceNotCheck.setCheckedState(Long.getLong(state));
//
//            char[] chars = state.toCharArray();
//            Arrays.sort(chars);
//            state = new String(chars);
//            if (state.contains("12")){
//                referenceNotCheck.setCheckedState(5L);
//                referenceNotCheck.setCheckedDate(date.toLocalDate());
//            }

            return repository.save(referenceNotCheck);

        }
        return null;
    }

    private double getEnergy(Long moduleId, LocalDateTime dateTime){
        LocalDateTime start = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);


        double energy = batteryRepository.getEnergyBetweenTimeAndModuleId(moduleId, start, dateTime);

        return energy;
    }
}
