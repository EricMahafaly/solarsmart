package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Double stockageMensuel;
    private Integer assistance; // ceci est le nombre de jours de l'assistance en une semaine.
    private String entretien;
    private String monitoring;
    private String remoteControl;
    private Boolean planing;
    private Boolean alert;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST
    , mappedBy = "subscription")
    private List<SubscriptionPrice> prices;
}
