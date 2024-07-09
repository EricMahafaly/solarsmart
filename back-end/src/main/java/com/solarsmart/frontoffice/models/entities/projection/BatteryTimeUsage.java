package com.solarsmart.frontoffice.models.entities.projection;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public interface BatteryTimeUsage extends Serializable {
    Long getModuleId();

    Long getBatteryId();
    LocalDateTime getDate();

//    public BatteryTimeUsage(Long moduleId, LocalDateTime date) {
//        this.moduleId = moduleId;
//        this.date = date;
//    }
}
