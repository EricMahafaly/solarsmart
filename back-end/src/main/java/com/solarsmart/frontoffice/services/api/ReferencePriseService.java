package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import com.solarsmart.frontoffice.services.api.base.BaseService;

import java.time.LocalDateTime;

public interface ReferencePriseService extends ReferenceService<ReferencePrise>, BaseService<ReferencePrise> {
    ReferencePrise handleIfConditionTrue(Long moduleId, LocalDateTime date);

}
