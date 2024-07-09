package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Composant;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.models.entities.RelayStateEnum;
import com.solarsmart.frontoffice.repositories.api.RelayRepository;
import com.solarsmart.frontoffice.services.api.SwitchableRelais;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
@RequiredArgsConstructor
public abstract class AbstractSwitchableRelais<C extends Composant> implements SwitchableRelais<C> {
    protected final RelayRepository relayRepository;

    protected abstract void saveComposant(C composant);

    @Override
    public RelaisState switchRelais(C composant) {
        RelaisState relaisState = composant.getRelaisState();
        RelaisState state = new RelaisState();
        if (relaisState.getState() == RelayStateEnum.HIGH){
            state = relayRepository.findByState(RelayStateEnum.LOW);
        } else if (relaisState.getState() == RelayStateEnum.LOW) {
            state = relayRepository.findByState(RelayStateEnum.HIGH);
        }

        composant.setRelaisState(state);

        this.saveComposant(composant);

        return state;
    }
}
