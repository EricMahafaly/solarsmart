package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.ReferenceBattery;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReferenceBatteryRepository extends BaseRepository<ReferenceBattery> {
    Optional<ReferenceBattery> findNotCheckedByModuleAndDate(Long moduleId, LocalDate date);

    Optional<ReferenceBattery> findByModuleId(long moduleId);
}
