package com.solarsmart.frontoffice.models.dto.response.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public abstract class FilterDate<T> {

    private T data;
    private String label;
    private String tag;

//    public abstract String getLabel();

    @JsonIgnore
    public abstract List<? extends FilterDate<T>> getListResult();

    public String getTag(){
        return this.tag;
    }


    public void setLabel(String label) {
        this.label = label;
        if (this.getLabel() != null) this.setTag(this.getLabel().substring(0,1).toUpperCase());
    }
}
