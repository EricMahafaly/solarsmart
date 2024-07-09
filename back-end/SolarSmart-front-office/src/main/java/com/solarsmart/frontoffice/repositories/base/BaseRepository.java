package com.solarsmart.frontoffice.repositories.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> {

    Optional<T> findById(Long id);

    Optional<T> findByExample(Example<T> example);

    T save(T entity);

    List<T> saveAll(List<T> entities);

    void deleteById(Long id);

    void delete(T entity);

    Page<T> findAll(int page, int pageSize);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(Example<T> example, Pageable pageable);
    List<T> findAll(Example<T> example, Sort sort);
    List<T> findAll(Example<T> example);

    List<T> findAll();

    List<T> findAll(Sort sort);

    void deleteAll(List<T> entities);

    long count();
}
