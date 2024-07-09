package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.dto.response.CustomerStatistic;
import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.projection.CustomerCount;
import com.solarsmart.frontoffice.services.api.base.BaseService;

import java.time.LocalDate;
import java.util.Optional;

public interface CustomerService extends BaseService<Customer> {

//    @Override
    Customer findByEmail(String email);

    Customer findByModuleId(Long id);

    Customer associate (Customer customer, ModuleSolar module);

    long getCustomerCount(int month, long year);

    long countCustomerBefore(LocalDate date);
}
