package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "report_state")
public class ReportState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    @ColumnTransformer(
            read = "REPLACE(state, ' ', '_')",
            write = "REPLACE(?, ' ', '_')"
    )
//    @Column(name = "state", columnDefinition = "enum('Closed','Open','In progress')")
    private ReportStateEnum state;

    private Integer value;

}
