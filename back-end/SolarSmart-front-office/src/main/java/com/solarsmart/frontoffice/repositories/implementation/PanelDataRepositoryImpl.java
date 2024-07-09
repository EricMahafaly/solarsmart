package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.api.PanelDataRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaPanelDataDetailRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaPanelDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
//public class PanelDataRepositoryImpl extends BaseRepositoryImpl<PanelData, JpaPanelDataRepository> implements PanelDataRepository {
public class PanelDataRepositoryImpl extends AbstractComposantDataRepository<PanelData> implements PanelDataRepository {
    private final JpaPanelDataRepository repository;
    public PanelDataRepositoryImpl(JpaPanelDataRepository repository, JpaPanelDataDetailRepository panelDataDetailRepository) {
        super(panelDataDetailRepository);
        this.repository = repository;
    }

    @Override
    public Page<PanelData> getAllById(Long id, int page, int pageSize) {
        return null;
    }

    @Override
    public Page<PanelData> getAllByModuleId(Long moduleId, int page, int pageSize) {
        return null;
    }

    @Override
    public PanelData findByModuleIdAndBetweenDate(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return repository.findPanelDataByPanel_Module_IdAndDateBetween(moduleId, date1, date2);
    }

    @Override
    public double getProductionBetweenTimeAndModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return this.repository.getProductionByModuleIdAndDateBetween(moduleId, date1, date2);
    }

    @Override
    public List<ComposantProjection> getAllProductionBetweenDateByModuleId(long moduleId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.repository.getAllProductionBetweenDateByModuleId(moduleId, startDate, endDate);
    }

    @Override
    public List<PanelData> findAll(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return repository.findByPanel_Module_IdAndDateBetween(moduleId, date1, date2);
    }
}
