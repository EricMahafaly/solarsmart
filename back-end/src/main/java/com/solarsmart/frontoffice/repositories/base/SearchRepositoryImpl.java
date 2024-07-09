package com.solarsmart.frontoffice.repositories.base;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@NoRepositoryBean
@RequiredArgsConstructor
//@Repository
public class SearchRepositoryImpl<T, R extends JpaSpecificationExecutor<T>> implements SearchRepository<T> {

    protected final R repository;
    @Override
    public Page<T> findAll(Pageable pageable, Specification<T> specification) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return repository.findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return repository.findAll(spec);
    }
}
