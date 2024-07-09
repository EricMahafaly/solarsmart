package com.solarsmart.frontoffice.models.entities;

import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    private Long id;

    @Column(name = "seen")
    private Boolean seen;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private ModuleSolar module;

//    public static Notification
}
