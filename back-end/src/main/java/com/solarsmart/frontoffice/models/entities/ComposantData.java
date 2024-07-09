package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public class ComposantData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

//    @Column(name = "tension", precision = 19, scale = 2)
    private Double tension;

//    @Column(name = "puissance", precision = 19, scale = 2)
    private Double puissance;

//    @Column(name = "courant", precision = 19, scale = 2)
    private Double courant;

}
