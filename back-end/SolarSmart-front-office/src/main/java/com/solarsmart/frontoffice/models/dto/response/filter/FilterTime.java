package com.solarsmart.frontoffice.models.dto.response.filter;

import java.time.LocalTime;
import java.util.List;

public class FilterTime<T> extends FilterDate<T> {
    private LocalTime hour;

    @Override
    public List<? extends FilterDate<T>> getListResult() {
        return null;
    }
}
