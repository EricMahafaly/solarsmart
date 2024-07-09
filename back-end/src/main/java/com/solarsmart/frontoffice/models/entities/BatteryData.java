package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "battery_data")
@Getter
@Setter
@ToString(callSuper = true)
public class BatteryData extends ComposantData{

//    @Column(name = "energy", precision = 19, scale = 2)
    private Double energy;

//    @Column(name = "pourcentage", precision = 5, scale = 2)
    private Double pourcentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "battery_id", nullable = false)
    @ToString.Exclude
    private Battery battery;

}
