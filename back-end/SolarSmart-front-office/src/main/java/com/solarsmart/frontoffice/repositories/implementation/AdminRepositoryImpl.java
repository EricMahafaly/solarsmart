package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.repositories.api.AdminRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaAdminRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin, JpaAdminRepository> implements AdminRepository {
    public AdminRepositoryImpl(JpaAdminRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.repository.existsByEmail(email);
    }
}
