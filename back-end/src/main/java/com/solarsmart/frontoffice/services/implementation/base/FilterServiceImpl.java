package com.solarsmart.frontoffice.services.implementation.base;

import com.solarsmart.frontoffice.repositories.base.SearchRepository;
import com.solarsmart.frontoffice.services.api.base.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
//@Service
public class FilterServiceImpl<T> implements FilterService<T> {
    private final SearchRepository<T> repository;
    @Override
    public List<T> filter(Specification<T> specifications) {
        return repository.findAll(specifications);
    }

    @Override
    public Page<T> filter(Specification<T> specification, Pageable pageable) {
        return this.repository.findAll(pageable, specification);
    }

    @Override
    public Optional<T> filterOneResult(Specification<T> specification) {
        return this.repository.findOne(specification);
    }
}
