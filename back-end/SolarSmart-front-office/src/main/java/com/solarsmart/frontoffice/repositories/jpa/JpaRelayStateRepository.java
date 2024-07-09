package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.models.entities.RelayStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRelayStateRepository extends JpaRepository<RelaisState, Long> {

    RelaisState findByState(RelayStateEnum state);
}
