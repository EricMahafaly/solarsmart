package com.solarsmart.frontoffice.models.specifications;

import com.solarsmart.frontoffice.models.base.filter.SearchCriteria;
import com.solarsmart.frontoffice.models.base.filter.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {
    private List<SearchCriteria> params;

    public SpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final SpecificationBuilder<T> with(String key,
                                              String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final SpecificationBuilder<T> with(List<SearchCriteria> searchCriteria){
        params = searchCriteria;
        return this;
    }

    public final SpecificationBuilder<T> with(SearchCriteria
                                                      searchCriteria){
        params.add(searchCriteria);
        return this;
    }



    public Specification<T> build(){
        if(params.isEmpty()) return null;

        Specification<T> result = new BaseSpecification<>(params.get(0));

        for (int i = 1; i < params.size(); i++){
            SearchCriteria criteria = params.get(i);

            result =  SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new BaseSpecification<>(criteria))
                    : Specification.where(result).or(new BaseSpecification<>(criteria));
        }
        return result;
    }
}
