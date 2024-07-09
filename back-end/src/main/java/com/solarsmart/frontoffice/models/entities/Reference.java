package com.solarsmart.frontoffice.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "checked_date", columnDefinition = "date default current_date", nullable = false)
    private LocalDate checkedDate = LocalDate.now();

    @Column(name = "checked_state", columnDefinition = "bigint default 0", nullable = false)
    private Long checkedState = 0L;

    @OneToOne
    @JoinColumn(name = "module_id")
    private ModuleSolar module;

    public String getSate(){
        String state = this.getCheckedState().toString();
        if (state.equals("0")) return "";
        return state;
    }

    public void setSate(LocalDateTime date){
        String state = this.getSate();
        this.setCheckedState(Long.getLong(state));

        char[] chars = this.getSate().toCharArray();
        Arrays.sort(chars);
        state = new String(chars);
        if (state.contains("12")){
            this.setCheckedState(5L);
            this.setCheckedDate(date.toLocalDate());
        }
    }

    public Reference(ModuleSolar module){
        this.setModule(module);
    }
}
