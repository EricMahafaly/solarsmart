package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.PriseData;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import com.solarsmart.frontoffice.services.api.base.ExportableData;

import java.time.LocalDateTime;
import java.util.List;

public interface PriseDataService extends BaseService<PriseData>, ExportableData<PriseData> {

    List<PriseData> list(long moduleId);

    List<PriseData> findAllBetweenDateByModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2);
}
