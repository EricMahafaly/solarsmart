package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reference_battery")
public class ReferenceBattery extends Reference{

    @Column(name = "duration", columnDefinition = "time default '08:00'", nullable = false)
    private LocalTime duration = LocalTime.of(8, 0);

    @Column(columnDefinition = "double precision default 80.0", nullable = false)
    private Double energy = 80.0;

    public ReferenceBattery(ModuleSolar module){
        super(module);
    }

    public double getDurationInSecond(){
        double heuresEnSecondes = this.duration.getHour() * 3600;
        double minutesEnSecondes = this.getDuration().getMinute() * 60;
        double secondes = this.getDuration().getSecond();

        return (heuresEnSecondes + minutesEnSecondes + secondes);
    }
}
