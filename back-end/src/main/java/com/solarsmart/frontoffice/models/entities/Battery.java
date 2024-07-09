package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "battery")
@Getter
@Setter
public class Battery extends Composant{

    private String marque;
    private Double puissance;
    private Double voltage;

}