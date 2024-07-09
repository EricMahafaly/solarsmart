package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.ComposantData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface JpaComposantDataRepository<C extends ComposantData> extends JpaRepository<C, Long> {
    List<ComposantProjection> getAllCourantByDateAndModuleId(Long moduleId, LocalDate date);
    List<ComposantProjection> getAllPuissanceByDateAndModuleId(Long moduleId, LocalDate date);
    List<ComposantProjection> getAllTensionByDateAndModuleId(Long moduleId, LocalDate date);
    List<C> getAllByModuleId(Long moduleId);

     List<ComposantDataInfo> getAllByModuleIdAndBetweenDate(long moduleId, LocalDateTime startDate, LocalDateTime endDate);

    ComposantDataInfo findByTimeAndModule(long moduleId, LocalDateTime startDate, LocalDateTime endDate);
}
