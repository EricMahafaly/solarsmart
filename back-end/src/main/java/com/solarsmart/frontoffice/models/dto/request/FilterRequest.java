package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.base.filter.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    private List<SearchCriteria> criteria;
    private String dataOption;
}
