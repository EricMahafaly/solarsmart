package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriseRepository extends BaseRepository<Prise> {

    Optional<Prise> getByModule(long moduleId);


    double getEnergyBetweenTimeAndModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2);
}
