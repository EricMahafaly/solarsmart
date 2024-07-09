package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.Prise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPriseRepository extends JpaRepository<Prise, Long> {
    Optional<Prise> findByModule_Id(Long id);
}