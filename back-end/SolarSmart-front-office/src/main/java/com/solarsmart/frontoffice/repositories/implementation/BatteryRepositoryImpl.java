package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.projection.BatteryDistinct;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.api.BatteryRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaBatteryDataRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaBatteryRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class BatteryRepositoryImpl extends BaseRepositoryImpl<Battery, JpaBatteryRepository> implements BatteryRepository {

    private final JpaBatteryDataRepository jpaBatteryDataRepository;

    public BatteryRepositoryImpl(JpaBatteryRepository repository, JpaBatteryDataRepository jpaBatteryDataRepository) {
        super(repository);
        this.jpaBatteryDataRepository = jpaBatteryDataRepository;
    }

    @Override
    public Optional<Battery> findByModuleId(long moduleId) {
        return repository.findByModule_Id(moduleId);
    }

    @Override
    public Double getCurrentPercentageByModuleId(long moduleId) {
//        this.findByModuleId(moduleId).orElseThrow();
        return jpaBatteryDataRepository.findPourcentageForLatestDateByModuleId(moduleId);
    }

    @Override
    public double getEnergyBetweenTimeAndModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return jpaBatteryDataRepository.getEnergyByModuleIdAndDateBetween(moduleId, date1, date2);
    }

    @Override
    public List<ComposantProjection> getEnergiesBetweenDateByModuleId(long moduleId, LocalDateTime startDate, LocalDateTime endDate) {
        return jpaBatteryDataRepository.getAllEnergyBetweenDateByModuleId(moduleId, startDate, endDate);
    }

    @Override
    public List<BatteryDistinct> getAllUnique() {
        return this.repository.findAllDistinct();
    }

}
