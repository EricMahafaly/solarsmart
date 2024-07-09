package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.projection.CustomerCount;
import com.solarsmart.frontoffice.repositories.api.CustomerRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaCustomerRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
//@AllArgsConstructor
public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, JpaCustomerRepository> implements CustomerRepository {
    private final JpaCustomerRepository repository;

    public CustomerRepositoryImpl(JpaCustomerRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findByModuleId(Long moduleId) {
        return repository.findByModule_Id(moduleId);
    }

    @Override
    public Optional<CustomerCount> getCustomerCountByMonth(int month, long year) {
        return this.repository.findCustomerCountByMonthAndYear(month, year);
    }

    @Override
    public long countCustomerBefore(LocalDate date) {
        return this.repository.countByRegistrationDateBefore(date);
    }

    @Override
    public List<CustomerCount> getClientsCountMonthly(long year) {
        return this.repository.findClientsCountByMonthAndYear(year);
    }
}
