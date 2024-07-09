package com.solarsmart.frontoffice.repositories.base;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

//@NoRepositoryBean
@AllArgsConstructor
public class BaseRepositoryImpl<T, R extends JpaRepository<T, Long>> implements BaseRepository<T> {
    protected final R repository;

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<T> findByExample(Example<T> example) {
        return this.repository.findOne(example);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        return this.repository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    public void deleteAll(List<T> entities){
        this.repository.deleteAll(entities);
    }

    @Override
    public Page<T> findAll(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Example<T> example, Pageable pageable) {
//        this.repository.saveAllAndFlush()
        return this.repository.findAll(example, pageable);
    }

    @Override
    public List<T> findAll(Example<T> example, Sort sort) {
        return this.repository.findAll(example, sort);
    }

    @Override
    public List<T> findAll(Example<T> example) {
        return this.repository.findAll(example);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return this.repository.findAll(sort);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
