package com.solarsmart.frontoffice.models.entities.projection;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.Target;
import java.time.LocalDateTime;


public interface ComposantDataInfo {
//    @Value("#{target.id}")
//    Long getId();

//    @Value("#{target.date}")
    LocalDateTime getDate();

//    @Value("#{target.tension}")
    Double getTension();

//    @Value("#{target.puissance}")
    Double getPuissance();

//    @Value("#{target.courant}")
    Double getCourant();
}