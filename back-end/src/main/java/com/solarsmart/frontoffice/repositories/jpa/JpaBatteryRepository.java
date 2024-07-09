package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.projection.BatteryDistinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaBatteryRepository extends JpaRepository<Battery, Long> {

    Optional<Battery> findByModule_Id(long moduleId);

    @Query(nativeQuery = true, value = "select voltage, puissance, marque from battery group by marque, puissance, voltage")
    List<BatteryDistinct> findAllDistinct();
}
