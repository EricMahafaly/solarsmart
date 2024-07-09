package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.projection.PanelDistinct;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface PanelRepository extends BaseRepository<Panel> {

//    Optional<Panel> findById(long id);

    Optional<Panel> findByModuleId(long moduleId);

    List<PanelDistinct> distinct();
}
