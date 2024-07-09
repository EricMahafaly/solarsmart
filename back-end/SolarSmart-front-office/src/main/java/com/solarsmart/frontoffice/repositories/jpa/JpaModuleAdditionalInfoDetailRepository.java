package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaModuleAdditionalInfoDetailRepository extends JpaRepository<ModuleAdditionalInfoDetail, Long>,
        JpaSpecificationExecutor<ModuleAdditionalInfoDetail> {
}