package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaReferencePriseRepository extends JpaRepository<ReferencePrise, Long> {
    Optional<ReferencePrise> findByModule_Id(Long id);
}