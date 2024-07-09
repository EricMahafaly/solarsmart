package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "planning_prise")
@Getter
@Setter
public class PlanningPrise extends Planning{

    @Column(name = "consommation")
    private Double consommation;

    @ManyToOne
    @JoinColumn(name = "prise_id", nullable = false)
    private Prise prise;

}
