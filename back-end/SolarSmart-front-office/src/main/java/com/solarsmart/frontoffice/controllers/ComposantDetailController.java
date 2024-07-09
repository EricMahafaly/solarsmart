package com.solarsmart.frontoffice.controllers;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoRepositoryBean
public interface ComposantDetailController {

    ResponseEntity<?> findDetailByTime(long moduleId, LocalDateTime date);

    ResponseEntity<?> getAllDetailByDate(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime);
}
