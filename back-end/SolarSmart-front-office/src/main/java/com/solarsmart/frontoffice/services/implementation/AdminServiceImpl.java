package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.repositories.api.AdminRepository;
import com.solarsmart.frontoffice.services.api.AdminService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, AdminRepository> implements AdminService {
    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public boolean existingByEmail(String email) {
        return this.repository.existsByEmail(email);
    }
}
