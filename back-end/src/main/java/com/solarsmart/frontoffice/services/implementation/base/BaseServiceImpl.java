package com.solarsmart.frontoffice.services.implementation.base;

import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

//@NoRepositoryBean
@RequiredArgsConstructor
public class BaseServiceImpl<T, R extends BaseRepository<T>> implements BaseService<T> {

    protected final R repository;
    @Override
    public List<T> list() {
        return repository.findAll();
    }

    @Override
    public List<T> list(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<T> list(int pageSize, int page) {
        return repository.findAll(page, pageSize);
    }

    @Override
    public Page<T> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<T> list(Example<T> example) {
        return this.repository.findAll(example);
    }

    @Override
    public Page<T> list(Example<T> example, Pageable pageable) {
        return this.repository.findAll(example, pageable);
    }

    @Override
    public List<T> list(T example) {
        return this.list(Example.of(example));
    }

    @Override
    public Page<T> list(T example, Pageable pageable) {
        return this.list(Example.of(example), pageable);
    }

    @Override
    public T get(Long id) {
        return this.getOne(id).orElseThrow(
                () -> new DomainException("cette donnée recherchée n'existe pas"));
    }

    @Override
    public Optional<T> get(T example) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id");


        Example<T> tExample = Example.of(example, matcher);

        return this.get(tExample);
    }

    @Override
    public Optional<T> get(Example<T> example) {
        return this.repository.findByExample(example);
    }

    @Override
    public Optional<T> getOne(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public long count() {
        return this.repository.count();
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> createMany(List<T> entity) {
        return this.repository.saveAll(entity);
    }

    @Override
    public T update(Long id, T entity) {
        this.get(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        T result = this.getOne(id).orElseThrow(() -> new DomainException("l'élément recherché n'existe pas"));
        this.delete(result);
    }



    @Override
    public void delete(T entity) {
        this.repository.delete(entity);
    }

    @Override
    public void deleteMany(List<T> entities) {
        this.repository.deleteAll(entities);
    }
}
