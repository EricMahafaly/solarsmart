package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Notification;
import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.repositories.api.PanelRepository;
import com.solarsmart.frontoffice.repositories.api.PlanningRepository;
import com.solarsmart.frontoffice.repositories.api.RelayRepository;
import com.solarsmart.frontoffice.services.api.NotificationService;
import com.solarsmart.frontoffice.services.api.RelayPanelService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RelaisPanelServiceImpl extends AbstractSwitchableRelais<Panel> implements RelayPanelService {
    private final PanelRepository panelRepository;

    public RelaisPanelServiceImpl(RelayRepository relayRepository, PanelRepository panelRepository) {
        super(relayRepository);
        this.panelRepository = panelRepository;
    }


    @Override
    protected void saveComposant(Panel composant) {
        this.panelRepository.save(composant);
    }

    @Override
    public void relayed(Panel composant, double courant) {

    }
}
