package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "prise")
@Data
public class Prise extends Composant{

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