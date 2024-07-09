package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.repositories.api.ModuleRepository;
import com.solarsmart.frontoffice.repositories.api.ReferencePriseRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaReferencePriseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class ReferencePriseRepositoryImpl extends BaseRepositoryImpl<ReferencePrise, JpaReferencePriseRepository> implements ReferencePriseRepository {

    private final JpaReferencePriseRepository jpaReferencePriseRepository;

    public ReferencePriseRepositoryImpl(JpaReferencePriseRepository repository,
                                        JpaReferencePriseRepository jpaReferencePriseRepository) {
        super(repository);
        this.jpaReferencePriseRepository = jpaReferencePriseRepository;
    }

    @Override
    public Optional<ReferencePrise> findByModuleId(long moduleId) {
        return jpaReferencePriseRepository.findByModule_Id(moduleId);
    }

    @Override
    public Optional<ReferencePrise> findNotCheckedByModuleAndDate(Long moduleId, LocalDate localDate) {
        return Optional.empty();
    }
}
