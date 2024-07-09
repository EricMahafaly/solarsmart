package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface JpaNotificationRepository extends JpaRepository<Notification, Long>, JpaSpecificationExecutor<Notification> {
    List<Notification> findByModule_IdOrderByDate(Long id);

    List<Notification> findByModule_IdAndSeenFalseOrderByDate(Long id);
//    List<Notification> findByModule_IdOrderByDate(Long id);

//    List<Notification> findAllByModule_IdAndOrderByDate(Long moduleId);
}