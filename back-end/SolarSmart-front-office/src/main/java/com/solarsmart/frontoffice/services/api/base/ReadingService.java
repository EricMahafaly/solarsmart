package com.solarsmart.frontoffice.services.api.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ReadingService<T> {

    List<T> list();

    List<T> list(Sort sort);

    Page<T> list(int pageSize, int page);

    Page<T> list(Pageable pageable);

    List<T> list(Example<T> example);

    Page<T> list(Example<T> example, Pageable pageable);

    List<T> list(T example);

    Page<T> list(T example, Pageable pageable);

    T get(Long id);

    Optional<T> get(T example);

    Optional<T> get(Example<T> example);

    Optional<T> getOne(Long id);

    long count();
}
