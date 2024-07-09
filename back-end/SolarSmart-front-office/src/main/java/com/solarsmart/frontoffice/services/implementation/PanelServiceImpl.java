package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.projection.PanelDistinct;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.repositories.api.PanelRepository;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.api.PanelService;
import com.solarsmart.frontoffice.services.api.RelaisService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelServiceImpl extends BaseServiceImpl<Panel, PanelRepository> implements PanelService {
    private final ModuleService moduleService;
    public PanelServiceImpl(PanelRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public Panel getByModuleId(long idModule) {
        ModuleSolar moduleSolar = this.moduleService.get(idModule);
        return repository.findByModuleId(moduleSolar.getId())
                .orElseThrow( () -> new DomainException("cette module ne possède aucune panel"));
    }

    @Override
    public List<PanelDistinct> getAllDistinct() {
        return repository.distinct();
    }
}
