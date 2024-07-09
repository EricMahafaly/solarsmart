package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModuleRepository extends BaseRepository<ModuleSolar> {

//    ModuleSolar save(ModuleSolar module);
    Page<ModuleSolar> findModulesUnused(Pageable pageable);
}
