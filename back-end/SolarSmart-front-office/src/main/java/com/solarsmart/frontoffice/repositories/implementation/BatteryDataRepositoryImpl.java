package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.repositories.api.BatteryDataRepository;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.repositories.jpa.JpaBatteryDataRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaBatteryDataDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BatteryDataRepositoryImpl extends AbstractComposantDataRepository<BatteryData> implements BatteryDataRepository {
    private final JpaBatteryDataRepository repository;
    public BatteryDataRepositoryImpl(JpaBatteryDataRepository repository, JpaBatteryDataDetailRepository batteryDetailRepository) {
        super(batteryDetailRepository);
        this.repository = repository;
    }

    @Override
    public Page<BatteryData> getAllById(Long id, int page, int pageSize) {
        Page<BatteryData> data = repository.findByBattery_Id(id, PageRequest.of(page, pageSize));
        return data;
    }

    @Override
    public Page<BatteryData> getAllByModuleId(Long moduleId, int page, int pageSize) {
        Page<BatteryData> data = repository.findByBattery_Module_Id(moduleId, PageRequest.of(page, pageSize));
//        return data;
        return data;
    }

    @Override
    public BatteryData findByModuleIdAndBetweenDate(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        BatteryData batteryData = repository.findBatteryDataByBattery_Module_IdAndDateBetween(moduleId, date1, date2);
        return batteryData;
    }

    @Override
    public Page<ComposantProjection> getAllTensionPaginateByDateAndModuleId(long moduleId, LocalDate date, Pageable pageable) {
        return this.repository.getAllTensionByDateAndModuleId(moduleId, date, pageable);
    }

    @Override
    public Page<BatteryData> findAllByModuleId(Long moduleId, Pageable pageable) {
        return this.repository.findByBattery_Module_Id(moduleId, pageable);
    }

    @Override
    public List<BatteryData> findAll(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return repository.findAllByDateBetweenAndBattery_Module_IdOrderByDateAsc(date1, date2, moduleId);
    }

//    @Override
//    public List<ComposantDataInfo> getAllDetail(long moduleId, LocalDateTime startDate, LocalDateTime endDate) {
//        return this.repository.findByDateBetweenAndBattery_Module_IdOrderByDate(startDate, endDate, moduleId);
//    }
}
