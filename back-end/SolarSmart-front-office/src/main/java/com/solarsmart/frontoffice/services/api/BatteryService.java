package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.projection.BatteryDistinct;
import com.solarsmart.frontoffice.services.api.base.BaseService;

import java.util.List;

public interface BatteryService extends BaseService<Battery> {

    Battery getByModule(Long moduleId);

    List<BatteryDistinct> getAllDistinct();

//    double getCurrentPercentageByModuleId(long moduleId);
}
