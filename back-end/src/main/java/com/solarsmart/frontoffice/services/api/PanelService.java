package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.projection.PanelDistinct;
import com.solarsmart.frontoffice.services.api.base.BaseService;

import java.util.List;

public interface PanelService extends BaseService<Panel> {
    Panel getByModuleId(long idModule);

    List<PanelDistinct> getAllDistinct();
}
