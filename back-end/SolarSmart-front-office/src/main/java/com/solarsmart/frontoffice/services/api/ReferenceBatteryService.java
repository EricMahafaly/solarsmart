package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.ReferenceBattery;
import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import com.solarsmart.frontoffice.services.api.base.BaseService;

import java.time.LocalDateTime;

public interface ReferenceBatteryService extends ReferenceService<ReferenceBattery>, BaseService<ReferenceBattery>{
    ReferenceBattery handleIfConditionTrue(Long moduleId, LocalDateTime date);
}
