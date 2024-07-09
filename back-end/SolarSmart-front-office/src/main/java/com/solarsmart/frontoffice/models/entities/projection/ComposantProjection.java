package com.solarsmart.frontoffice.models.entities.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public interface ComposantProjection {
     Double getValue();
     LocalDateTime getDate();
}
