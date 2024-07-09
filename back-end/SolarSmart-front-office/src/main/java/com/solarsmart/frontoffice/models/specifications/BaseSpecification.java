package com.solarsmart.frontoffice.models.specifications;

import com.solarsmart.frontoffice.models.base.filter.SearchCriteria;
import com.solarsmart.frontoffice.models.base.filter.SearchOperation;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNullApi;

import java.util.Objects;

@Getter
public class BaseSpecification<T> implements Specification<T> {
    protected final SearchCriteria searchCriteria;

    public BaseSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Object value = searchCriteria.getValue();
        String strToSearch = null;

        if (value != null) strToSearch = value.toString().toLowerCase();

        String[] keys = searchCriteria.getFilterKey().split("\\.");
        Path path = root;
        for (String key : keys) {
            path = path.get(key);
        }


        Class<?> javaType = path.getJavaType();
        boolean isString = javaType.equals(String.class);

        return switch (
                Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))
                ) {
            case CONTAINS -> isString ? criteriaBuilder.like(
                    criteriaBuilder.lower((Expression<String>) path),
                    "%" + strToSearch + "%") : null;

            case DOES_NOT_CONTAIN -> isString ? criteriaBuilder.notLike(
                    criteriaBuilder.lower((Expression<String>)path),
                    "%" + strToSearch + "%") : null;

            case EQUAL -> criteriaBuilder.equal(path, value);

            case LESS_THAN_EQUAL -> criteriaBuilder.lessThanOrEqualTo(
                    path, strToSearch
            );

            case GREATER_THAN_EQUAL -> criteriaBuilder.greaterThanOrEqualTo(
                    path, strToSearch
            );

            case NOT_NULL -> criteriaBuilder.isNotNull(
                    path
            );

            default -> criteriaBuilder.isNull(
                    path
            );
        };


        //        return switch (
//                Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))
//                ) {
//            case CONTAINS -> criteriaBuilder.like(
//                    criteriaBuilder.lower(root
//                            .get(searchCriteria.getFilterKey())),
//                    "%" + strToSearch + "%");
//
//            case DOES_NOT_CONTAIN -> criteriaBuilder.notLike(criteriaBuilder.lower(root
//                            .get(searchCriteria.getFilterKey())),
//                    "%" + strToSearch + "%");
//
//            case EQUAL -> criteriaBuilder.equal(
//                    criteriaBuilder.lower(root
//                            .get(searchCriteria.getFilterKey())),
//                    value);
//            case LESS_THAN_EQUAL -> criteriaBuilder.lessThanOrEqualTo(
//                    root.get(searchCriteria.getFilterKey()), strToSearch);
//
//            case GREATER_THAN_EQUAL -> criteriaBuilder.greaterThanOrEqualTo(
//                    root.get(searchCriteria.getFilterKey()), strToSearch);
//
//            case NOT_NULL -> criteriaBuilder.isNotNull(
//                    root.get(searchCriteria.getFilterKey()));
//
//
//            default -> criteriaBuilder.isNull(
//                    root.get(searchCriteria.getFilterKey()));
//        };
    }
}
