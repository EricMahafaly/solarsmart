package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.models.views.PriseConsommation;
import com.solarsmart.frontoffice.models.views.TimeUsageDaily;
import com.solarsmart.frontoffice.repositories.api.PriseConsommationRepository;
import com.solarsmart.frontoffice.repositories.api.PriseTimeUsageRepository;
import com.solarsmart.frontoffice.repositories.api.ReferencePriseRepository;
import com.solarsmart.frontoffice.services.api.NotificationService;
import com.solarsmart.frontoffice.services.api.ReferencePriseService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReferencePriseServiceImpl extends BaseServiceImpl<ReferencePrise, ReferencePriseRepository>
        implements ReferencePriseService {

    private final PriseConsommationRepository priseConsommationRepository;
    private final PriseTimeUsageRepository priseTimeUsageRepository;
    private final NotificationService notificationService;
    public ReferencePriseServiceImpl(ReferencePriseRepository repository, PriseConsommationRepository priseConsommationRepository,
                                     PriseTimeUsageRepository priseTimeUsageRepository,
                                     NotificationService notificationService) {
        super(repository);
        this.priseConsommationRepository = priseConsommationRepository;
        this.priseTimeUsageRepository = priseTimeUsageRepository;
        this.notificationService = notificationService;
    }

    @Override
    public ReferencePrise findByModuleId(Long moduleId){
        return repository.findByModuleId(moduleId)
                .orElseThrow(()-> new DomainException("cette module ne possède aucune reference prise"));
    }

    @Override
    public ReferencePrise toDefault(Long moduleId) {
        ReferencePrise referencePrise = this.findByModuleId(moduleId);

        ReferencePrise newRef = new ReferencePrise();

        newRef.setModule(referencePrise.getModule());
        newRef.setId(referencePrise.getId());

        return repository.save(newRef);
    }

    @Override
    public ReferencePrise handleIfConditionTrue(Long moduleId, LocalDateTime date) {

        ReferencePrise referenceNotCheck = repository.findNotCheckedByModuleAndDate(moduleId, date.toLocalDate())
                .orElse(null);


        if (referenceNotCheck != null){

//            TimeUsageDaily timeUsageDaily = priseTimeUsageRepository
//                    .getTimeUsageByModuleAndDate(moduleId, date.toLocalDate()).orElse(new TimeUsageDaily());

            TimeUsageDaily timeUsageDaily = new TimeUsageDaily();

            double duration = timeUsageDaily.getDuration();

            double consommation = this.getConsommation(moduleId, date);

            String state = referenceNotCheck.getSate();

            if (!state.contains("1") && duration <= referenceNotCheck.getDurationInSecond()){
                state += 1;
                Notification notification = new Notification();
                notification.setMessage("la durée d'utilisation de la prise a atteint la limite du reference à "+ date);
                notification.setDate(date);

                notificationService.create(moduleId, notification);
            }

            if (!state.contains("2") && consommation <= referenceNotCheck.getConsommation()){
                state += 2;
                Notification notification = new Notification();
                notification.setMessage("la consommation de la prise a atteint a atteint la limite du reference à "+ date);
                notification.setDate(date);

                notificationService.create(moduleId, notification);
            }

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

            repository.save(referenceNotCheck);

        }
        return null;
    }

    private double getConsommation(Long moduleId, LocalDateTime dateTime){

        PriseConsommation priseConsommations = priseConsommationRepository
                .findAllByModuleIdAndDateBetween(moduleId, dateTime.toLocalDate(), dateTime.toLocalDate()).get(0);

        double consommation = priseConsommations.getConsommation();

        return consommation;
    }
}
