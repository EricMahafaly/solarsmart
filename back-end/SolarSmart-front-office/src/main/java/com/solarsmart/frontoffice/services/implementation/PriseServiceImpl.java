package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.repositories.api.PriseRepository;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.api.PriseService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PriseServiceImpl extends BaseServiceImpl<Prise, PriseRepository> implements PriseService {
    private final ModuleService moduleService;
    public PriseServiceImpl(PriseRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public Prise getByModuleId(long idModule) {
        ModuleSolar moduleSolar = this.moduleService.get(idModule);
        return repository.getByModule(moduleSolar.getId())
                .orElseThrow(() -> new DomainException("cette module ne possède aucune prise"));
    }
}
