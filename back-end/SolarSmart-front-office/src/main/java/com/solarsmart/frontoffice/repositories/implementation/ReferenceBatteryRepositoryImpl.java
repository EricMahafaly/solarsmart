package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.ReferenceBattery;
import com.solarsmart.frontoffice.repositories.api.ReferenceBatteryRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaReferenceBatteryRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class ReferenceBatteryRepositoryImpl extends
        BaseRepositoryImpl<ReferenceBattery, JpaReferenceBatteryRepository> implements ReferenceBatteryRepository {

    private final JpaReferenceBatteryRepository jpaReferenceBatteryRepository;

    public ReferenceBatteryRepositoryImpl(JpaReferenceBatteryRepository repository) {
        super(repository);
        this.jpaReferenceBatteryRepository = repository;
    }

    @Override
    public Optional<ReferenceBattery> findNotCheckedByModuleAndDate(Long moduleId, LocalDate date) {
        return repository.findMayBeChecked(moduleId, date);
    }

    @Override
    public Optional<ReferenceBattery> findByModuleId(long moduleId) {
        return jpaReferenceBatteryRepository.findByModule_Id(moduleId);
    }
}
