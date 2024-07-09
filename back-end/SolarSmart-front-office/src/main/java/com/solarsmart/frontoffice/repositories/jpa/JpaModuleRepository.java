package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JpaModuleRepository extends JpaRepository<ModuleSolar, Long>, JpaSpecificationExecutor<ModuleSolar> {

    @Query("select m from ModuleSolar m left join Customer c where c.id IS NULL")
    Page<ModuleSolar> findModulesUnused(Pageable pageable);
}
