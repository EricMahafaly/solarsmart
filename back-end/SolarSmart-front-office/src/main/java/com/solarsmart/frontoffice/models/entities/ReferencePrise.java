package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reference_prise")
public class ReferencePrise extends Reference{


    @Column(columnDefinition = "time default '08:00'", nullable = false)
    private LocalTime duration = LocalTime.of(8,0);

    @Column(columnDefinition = "double precision default 80", nullable = false)
    private Double consommation = 80.0;

    @OneToOne
    @JoinColumn(name = "module_id")
    private ModuleSolar module;

    public ReferencePrise(ModuleSolar module){
        super(module);
    }

    public double getDurationInSecond(){
        double heuresEnSecondes = this.duration.getHour() * 3600;
        double minutesEnSecondes = this.getDuration().getMinute() * 60;
        double secondes = this.getDuration().getSecond();

        return (heuresEnSecondes + minutesEnSecondes + secondes);
    }

//    @PrePersist
//    void preInsert() {
//        if (this.defaultConsommation == null) this.defaultConsommation = 80.;
//        if (this.defaultDuration == null) this.setDefaultDuration(LocalTime.of(8, 0));
//
//        if (this.duration == null) this.duration = this.getDefaultDuration();
//        if (this.consommation == null) this.consommation = this.getDefaultConsommation();
//    }
}
