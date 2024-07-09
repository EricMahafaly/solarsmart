package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.ReferenceBattery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface JpaReferenceBatteryRepository extends JpaRepository<ReferenceBattery, Long> {

    @Query("select r from ReferenceBattery r where r.module.id = ?1 and " +
            "(r.checkedDate < ?2 or (r.checkedDate = ?2 and r.checkedState != 5))")
    Optional<ReferenceBattery> findMayBeChecked(Long moduleId, LocalDate date);

    Optional<ReferenceBattery> findByModule_Id(Long id);
}