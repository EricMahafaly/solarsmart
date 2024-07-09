package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.views.PanelUsageDaily;
import com.solarsmart.frontoffice.models.views.PanelUsageMonthly;

import java.util.List;

public interface PanelProductionRepository {

    List<PanelUsageDaily> findAllByModuleIdAndMonthAndYear(long moduleId, int month, long year);
    List<PanelUsageDaily> findAllByPanelIdAndMonthAndYear(long panelId, int month, long year);
    List<PanelUsageMonthly> findAllByPanelIdAndYear(long panelId, long year);
}
