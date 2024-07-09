package com.solarsmart.frontoffice.services.api.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface FilterService<T> {

    List<T> filter(Specification<T> specifications);

    Page<T> filter(Specification<T> specification, Pageable pageable);

    Optional<T> filterOneResult(Specification<T> specification);
}
