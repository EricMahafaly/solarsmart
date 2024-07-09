package com.solarsmart.frontoffice.models.dto.response.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class FilterDaily<T> extends FilterDate<T> {
    private LocalDate date;
    @JsonIgnore
    private List<FilterDaily<T>> filterDailies;

    public FilterDaily(LocalDate date){
        this.setLabel(date.getDayOfWeek().name());
        this.setTag(this.getLabel().substring(0, 1).toUpperCase());
        this.setDate(date);
    }

    public void init(LocalDate start, LocalDate end){
        if (this.filterDailies == null){
            this.filterDailies = new ArrayList<>();
            LocalDate current = start;
            while (!current.isAfter(end)){

                FilterDaily<T> day = new FilterDaily<>();
                day.setLabel(current.getDayOfWeek().name());
                day.setDate(current);

                this.filterDailies.add(day);

                current = current.plusDays(1);
            }
        }
    }

    public void init(LocalDate start, LocalDate end, T data){
        if (this.filterDailies == null){
            this.filterDailies = new ArrayList<>();

            LocalDate current = start;
            while (!current.isAfter(end)){

                FilterDaily<T> day = new FilterDaily<>();
                day.setLabel(current.getDayOfWeek().name());
                day.setDate(current);
                day.setData(data);
                this.filterDailies.add(day);

                current = current.plusDays(1);
            }
        }
    }

//    @Override
//    public void setLabel(String label) {
//        super.setLabel(label);
//        if (this.getLabel() != null) this.setTag(this.getLabel().substring(0,1).toUpperCase());
//    }

    public void setData(LocalDate date, T data) {
        int size = this.getListResult().size();
        for (int i = 0; i < size; i++) {
            if (date.isEqual(getListResult().get(i).getDate())){
                getListResult().get(i).setData(data);
            }
        }
    }

    @Override
    public List<FilterDaily<T>> getListResult() {
        return this.filterDailies;
    }

    public static void main(String[] args) {
        FilterWeekly<Double> filterWeekly = FilterWeekly.getInstance(1, 2025, 0.2);

        filterWeekly.setData(LocalDate.now(), 0.3);

        System.out.println(filterWeekly.getListResult());
    }
}
