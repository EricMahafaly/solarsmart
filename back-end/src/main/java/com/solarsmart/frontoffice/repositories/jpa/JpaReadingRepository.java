package com.solarsmart.frontoffice.repositories.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface JpaReadingRepository<T, ID> extends Repository<T, ID> {

     List<T> findAll();

     Page<T> findAll(Pageable pageable);

     long count();

     Optional<T> findById(ID id);
}
