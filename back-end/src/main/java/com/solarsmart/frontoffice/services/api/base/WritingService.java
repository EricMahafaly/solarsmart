package com.solarsmart.frontoffice.services.api.base;

import java.util.List;

public interface WritingService<T> {

    T create(T entity);

    List<T> createMany(List<T> entity);

    T update(Long id, T entity);

    void delete(Long id);

    void delete(T entity);

    void deleteMany(List<T> entities);
}
