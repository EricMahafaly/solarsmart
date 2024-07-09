package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrator")
public class Admin extends User {
    private String name;
    private String lastName;

    @Transient
    private String[] roles = new String[]{"ADMIN"};

}
