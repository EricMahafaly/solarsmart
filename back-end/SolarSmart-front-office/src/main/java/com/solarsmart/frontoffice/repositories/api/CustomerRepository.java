package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.projection.CustomerCount;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer> {
    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByModuleId(Long moduleId);

    Optional<CustomerCount> getCustomerCountByMonth(int month, long year);

    long countCustomerBefore(LocalDate date);
    List<CustomerCount> getClientsCountMonthly( long year);
}
