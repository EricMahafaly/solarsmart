package com.solarsmart.frontoffice.models.entities;

import lombok.Getter;

@Getter
public enum ReportStateEnum {
    CLOSED("Closed"),
    IN_PROGRESS("In progress");

    private String value;

    ReportStateEnum(String value) {
        this.value = value;
    }

    public static ReportStateEnum fromValue(String value) {
        for (ReportStateEnum state : ReportStateEnum.values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid ReportStateEnum value: " + value);
    }
}

