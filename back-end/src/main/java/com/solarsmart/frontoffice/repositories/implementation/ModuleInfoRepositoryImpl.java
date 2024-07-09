package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfo;
import com.solarsmart.frontoffice.repositories.api.ModuleInfoRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaModuleAdditionalInfoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ModuleInfoRepositoryImpl extends BaseRepositoryImpl<ModuleAdditionalInfo, JpaModuleAdditionalInfoRepository> implements ModuleInfoRepository {
    public ModuleInfoRepositoryImpl(JpaModuleAdditionalInfoRepository repository) {
        super(repository);
    }
}
