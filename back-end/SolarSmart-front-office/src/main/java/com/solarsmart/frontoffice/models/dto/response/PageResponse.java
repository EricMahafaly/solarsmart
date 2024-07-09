package com.solarsmart.frontoffice.models.dto.response;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> items;
    private long totalItems;
    private int totalPages;

    public PageResponse(List<T> items, long totalItems, int totalPages) {
        this.items = items;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }
}
