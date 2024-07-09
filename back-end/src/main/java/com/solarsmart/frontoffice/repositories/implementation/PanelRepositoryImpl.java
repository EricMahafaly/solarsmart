package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.projection.PanelDistinct;
import com.solarsmart.frontoffice.repositories.api.PanelRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaPanelRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@AllArgsConstructor
@Repository
public class PanelRepositoryImpl extends BaseRepositoryImpl<Panel, JpaPanelRepository> implements PanelRepository {
//    private final JpaPanelRepository jpaPanneauRepository;

    public PanelRepositoryImpl(JpaPanelRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Panel> findByModuleId(long moduleId) {
        return repository.findByModule_Id(moduleId);
    }

    @Override
    public List<PanelDistinct> distinct() {
        return repository.getAllDistinct();
    }
}
