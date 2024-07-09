package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfo;
import com.solarsmart.frontoffice.repositories.api.ModuleInfoRepository;
import com.solarsmart.frontoffice.services.api.ModuleAdditionalInfoService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ModuleAdditionalInfoServiceImpl extends BaseServiceImpl<ModuleAdditionalInfo, ModuleInfoRepository> implements ModuleAdditionalInfoService {
    public ModuleAdditionalInfoServiceImpl(ModuleInfoRepository repository) {
        super(repository);
    }
}
