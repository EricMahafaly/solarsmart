package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.ComposantData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ComposantDataRepository<D extends ComposantData> {
    Page<D> getAllById(Long id, int page, int pageSize);
    Page<D> getAllByModuleId(Long moduleId, int page, int pageSize);
    List<D> getAllByModuleId(Long moduleId);
    D findByModuleIdAndBetweenDate(long moduleId, LocalDateTime date1, LocalDateTime date2);

    List<ComposantDataInfo> getAllDetail(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    ComposantDataInfo findByTimeAndModule(long moduleId, LocalDateTime startDate, LocalDateTime endDate);
}
