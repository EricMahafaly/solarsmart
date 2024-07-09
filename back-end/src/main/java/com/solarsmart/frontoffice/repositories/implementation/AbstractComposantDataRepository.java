package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.ComposantData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.api.ComposantDataRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaComposantDataRepository;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractComposantDataRepository<C extends ComposantData> extends BaseRepositoryImpl<C, JpaComposantDataRepository<C>> implements ComposantDataRepository<C> {

    public AbstractComposantDataRepository(JpaComposantDataRepository<C> repository) {
        super(repository);
    }

    @Override
    public abstract Page<C> getAllById(Long id, int page, int pageSize);

    @Override
    public abstract Page<C> getAllByModuleId(Long moduleId, int page, int pageSize);

    @Override
    public List<C> getAllByModuleId(Long moduleId) {
        return repository.getAllByModuleId(moduleId);
    }

    @Override
    public abstract C findByModuleIdAndBetweenDate(long moduleId, LocalDateTime date1, LocalDateTime date2);

    @Override
    public List<ComposantDataInfo> getAllDetail(long moduleId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.repository.getAllByModuleIdAndBetweenDate(moduleId, startDate, endDate);
    }

    @Override
    public ComposantDataInfo findByTimeAndModule(long moduleId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.repository.findByTimeAndModule(moduleId, startDate, endDate);
    }
}
