package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.PriseData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.api.PriseDataRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaPriseDataDetailRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaPriseDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriseDataRepositoryImpl extends AbstractComposantDataRepository<PriseData> implements PriseDataRepository {
    private final JpaPriseDataRepository repository;
    public PriseDataRepositoryImpl(JpaPriseDataRepository repository, JpaPriseDataDetailRepository priseDataDetailRepository) {
        super(priseDataDetailRepository);
        this.repository = repository;
    }
    @Override
    public PriseData save(PriseData priseData) {
        return repository.save(priseData);
    }

    @Override
    public List<ComposantProjection> getConsommationsBetweenDateByModuleId(Long moduleId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.repository.getAllConsommationsBetweenDateByModuleId(moduleId,startDate, endDate );
    }

    @Override
    public double getConsommationBetweenTimeAndModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return repository.getConsommationByModuleIdAndDate(moduleId, date1, date2);
    }

    @Override
    public List<PriseData> findAll(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return repository.findByPrise_Module_IdAndDateBetween(moduleId, date1, date2);
    }

    @Override
    public Page<PriseData> getAllById(Long id, int page, int pageSize) {
        return null;
    }

    @Override
    public Page<PriseData> getAllByModuleId(Long moduleId, int page, int pageSize) {
        return null;
    }

    @Override
    public PriseData findByModuleIdAndBetweenDate(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return repository.findPriseDataByPrise_Module_IdAndDateBetween(moduleId, date1, date2);
    }
}
