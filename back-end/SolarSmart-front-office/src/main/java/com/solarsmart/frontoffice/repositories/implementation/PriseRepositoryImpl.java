package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.repositories.api.PriseRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaPriseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

//@AllArgsConstructor
@Repository
public class PriseRepositoryImpl extends BaseRepositoryImpl<Prise, JpaPriseRepository> implements PriseRepository {
//    private final JpaPriseRepository jpaPriseRepository;

    public PriseRepositoryImpl(JpaPriseRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Prise> getByModule(long moduleId) {
        return repository.findByModule_Id(moduleId);
    }

    @Override
    public double getEnergyBetweenTimeAndModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return 0;
    }
}
