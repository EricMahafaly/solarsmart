package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "module_info")
@ToString
public class ModuleAdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    private String description;

    @OneToMany(mappedBy = "moduleAdditionalInfo", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ModuleAdditionalInfoDetail> details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
//    @ToString.Exclude
    private ModuleSolar module;
}
