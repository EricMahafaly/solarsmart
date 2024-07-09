package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReferencePriseRepository extends BaseRepository<ReferencePrise> {
    Optional<ReferencePrise> findByModuleId(long moduleId);

    Optional<ReferencePrise> findNotCheckedByModuleAndDate(Long moduleId, LocalDate localDate);
}
