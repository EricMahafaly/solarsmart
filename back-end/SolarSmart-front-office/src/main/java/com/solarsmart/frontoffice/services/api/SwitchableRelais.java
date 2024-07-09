package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Composant;
import com.solarsmart.frontoffice.models.entities.RelaisState;

public interface SwitchableRelais<C extends Composant> {

    RelaisState switchRelais(C composant);

    void relayed(C composant, double courant);
}
