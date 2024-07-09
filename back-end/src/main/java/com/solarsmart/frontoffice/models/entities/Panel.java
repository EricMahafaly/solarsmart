package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "panneau")
@Getter
@Setter
public class Panel extends Composant{

//    @ManyToOne
//    @JoinColumn(name = "panel_type_id")
//    private PanelType panelType;

    private String marque;
    private Double puissance;
    private Double voltage;



//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "module_id", nullable = false)
//    private ModuleSolar module;
//
//    @ManyToOne
//    @JoinColumn(name = "relais_state_id", nullable = false)
//    private RelaisState relaisState;

    // Other fields, getters, and setters

}
