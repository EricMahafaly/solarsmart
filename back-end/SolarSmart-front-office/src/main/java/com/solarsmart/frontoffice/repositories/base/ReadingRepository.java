package com.solarsmart.frontoffice.repositories.base;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ReadingRepository<T, ID> {

    Optional<T> findById(ID id);

    Page<T> findAll(int page, int pageSize);

    List<T> findAll();

    long count();
}
