package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import com.solarsmart.frontoffice.services.api.base.ExportableData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface BatteryDataService extends BaseService<BatteryData>, ExportableData<BatteryData> {
    List<BatteryData> getByModuleId(Long idModule);

    Page<BatteryData> getByModuleId(Long idModules, Pageable pageable);

    List<BatteryData> findAllBetweenDateByModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2);
    List<BatteryData> findAllBetweenTimeByModuleId(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime);
}
