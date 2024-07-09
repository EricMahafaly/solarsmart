package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "panneau_data")
@Data
public class PanelData extends ComposantData{

//    @Column(name = "production", precision = 19, scale = 2)
    private Double production;

    @ManyToOne
    @JoinColumn(name = "panneau_id", nullable = false)
    private Panel panel;

}
