package com.solarsmart.frontoffice.models.dto.request;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PageBuilderRequest {
    private int page;
    private int pageSize;
    private String sortBy;
    private String direction;

    public void setPage(int page) {
        if (page < 1) page = 1;
        this.page = page - 1;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) pageSize = 10;
        this.pageSize = pageSize;
    }

    public Pageable build(){
        String sortBy = this.getSortBy();
        String direction = this.getDirection();
        Sort.Direction sortDirection;

        if(this.getSortBy() == null || this.getSortBy().isBlank()){
            sortBy = "id";
        }
        if(this.getDirection() == null || this.getDirection().isBlank()){
            direction = "DESC";
        }
        sortDirection = Sort.Direction.fromString(direction);
        return PageRequest.of(this.getPage(), this.getPageSize(), sortDirection, sortBy);

    }
}
