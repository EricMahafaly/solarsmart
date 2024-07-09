package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.models.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<? extends User> findByEmail(String email);

    boolean existingByEmail(String  email);
}
