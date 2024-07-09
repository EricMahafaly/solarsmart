package com.solarsmart.frontoffice.repositories.base;

import com.solarsmart.frontoffice.repositories.jpa.JpaReadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
@RequiredArgsConstructor
public class ReadingRepositoryImpl<T, ID, R extends JpaReadingRepository<T, ID>> implements ReadingRepository<T, ID> {
    protected final R repository ;
    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Page<T> findAll(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }
}
