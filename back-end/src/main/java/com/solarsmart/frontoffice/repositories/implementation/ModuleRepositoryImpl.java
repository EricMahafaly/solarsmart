package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.repositories.api.ModuleRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaModuleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
//@AllArgsConstructor
public class ModuleRepositoryImpl extends BaseRepositoryImpl<ModuleSolar, JpaModuleRepository> implements ModuleRepository {
    public ModuleRepositoryImpl(JpaModuleRepository repository) {
        super(repository);
    }

    @Override
    public Page<ModuleSolar> findModulesUnused(Pageable pageable) {
        return this.repository.findModulesUnused(pageable);
    }
//    private final JpaModuleRepository jpaModuleRepository;
//    @Override
//    public ModuleSolar save(ModuleSolar module) {
//        return jpaModuleRepository.save(module);
//    }
}
