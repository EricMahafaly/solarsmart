package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "prise_data")
@Data
public class PriseData extends ComposantData{

//    @Column(name = "consommation", precision = 19, scale = 2)
    private Double consommation;

    @ManyToOne
    @JoinColumn(name = "prise_id", nullable = false)
    private Prise prise;

    // Other fields, getters, and setters

}
