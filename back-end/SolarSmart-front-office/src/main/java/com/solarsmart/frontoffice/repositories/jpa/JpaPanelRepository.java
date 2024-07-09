package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.projection.PanelDistinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaPanelRepository extends JpaRepository<Panel, Long> {
    Optional<Panel> findByModule_Id(Long module_id);

    @Query(value = "select voltage, puissance, marque from panneau group by marque, puissance, voltage", nativeQuery = true)
    List<PanelDistinct> getAllDistinct();
}
