package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaModuleAdditionalInfoRepository extends JpaRepository<ModuleAdditionalInfo, Long>, JpaSpecificationExecutor<ModuleAdditionalInfo> {
}