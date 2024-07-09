package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.projection.BatteryDistinct;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.repositories.api.BatteryRepository;
import com.solarsmart.frontoffice.services.api.BatteryService;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryServiceImpl extends BaseServiceImpl<Battery, BatteryRepository> implements BatteryService {
    private final ModuleService moduleService;
    public BatteryServiceImpl(BatteryRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public Battery getByModule(Long moduleId) {
        this.moduleService.get(moduleId);
        return repository.findByModuleId(moduleId).orElseThrow(()->
                new DomainException("cette module ne possède aucune battery"));
    }

    @Override
    public List<BatteryDistinct> getAllDistinct() {
        return this.repository.getAllUnique();
    }

    //    @Override
//    public double getCurrentPercentageByModuleId(long moduleId) {
//        this.moduleService.get(moduleId);
//        Double percentage = this.repository.getCurrentPercentageByModuleId(moduleId);
//        return percentage != null ? percentage : 0.0;
//    }
}
