package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "module")
@ToString
public class ModuleSolar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reference")
    private String reference;
    @Column(name = "ssid")
    private String ssid;
    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "module")
//    @Transient
    private Customer customer;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


    @OneToOne(mappedBy = "module", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Battery battery;
    @OneToOne(mappedBy = "module", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Panel panel;
    @OneToOne(mappedBy = "module", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Prise prise;

    @OneToOne(mappedBy = "module", cascade = CascadeType.REMOVE)
    private ReferencePrise referencePrise;
    @OneToOne(mappedBy = "module", cascade = CascadeType.REMOVE)
    private ReferenceBattery referenceBattery;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    private List<ModuleAdditionalInfo> otherInfo;
}
