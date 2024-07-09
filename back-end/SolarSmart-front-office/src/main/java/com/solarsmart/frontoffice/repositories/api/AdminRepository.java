package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.util.Optional;

public interface AdminRepository extends BaseRepository<Admin> {
    Optional<Admin> findByEmail(String email);

    boolean existsByEmail(String email);
}
