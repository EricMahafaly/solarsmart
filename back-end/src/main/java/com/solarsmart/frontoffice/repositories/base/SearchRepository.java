package com.solarsmart.frontoffice.repositories.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface SearchRepository<T> {

    Page<T> findAll(Pageable pageable, Specification<T> specification);

    Optional<T> findOne(Specification<T> spec);

    List<T> findAll(Specification<T> spec);
}
