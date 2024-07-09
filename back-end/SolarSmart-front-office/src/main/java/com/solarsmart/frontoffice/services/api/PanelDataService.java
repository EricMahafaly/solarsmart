package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import com.solarsmart.frontoffice.services.api.base.ExportableData;

import java.time.LocalDateTime;
import java.util.List;

public interface PanelDataService extends BaseService<PanelData>, ExportableData<PanelData> {

    List<PanelData> list(long moduleId);

    List<PanelData> findAllBetweenDateByModuleId(long moduleId, LocalDateTime startDate, LocalDateTime endDate);
}
