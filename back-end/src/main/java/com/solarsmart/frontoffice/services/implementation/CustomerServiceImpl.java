package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.dto.response.CustomerStatistic;
import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.projection.CustomerCount;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.repositories.api.CustomerRepository;
import com.solarsmart.frontoffice.services.api.CustomerService;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, CustomerRepository> implements CustomerService {
    private final ModuleService moduleService;
    public CustomerServiceImpl(CustomerRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public Customer findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(()-> new DomainException("email n'existe pas"));
    }

    @Override
    public Customer findByModuleId(Long id) {
        ModuleSolar moduleSolar = this.moduleService.get(id);
        return repository.findByModuleId(moduleSolar.getId()).orElse(null);
    }

    @Override
    public Customer associate(Customer customer, ModuleSolar module) {
        Customer customerWithModule = this.findByModuleId(module.getId());
        if (customerWithModule != null)
            throw new DomainException("cette module est déjà utilisé par une autre personne");

        customer.setModule(module);

        return customer;
    }

    @Override
    public long getCustomerCount(int month, long year) {

        if (month == 0){
            month = 12;
            year = year - 1;
        }else if (month <= 0) month = 1;

//        LocalDate date =

        CustomerCount count = this.repository.getCustomerCountByMonth(month, year).orElse(null);
        if (count == null) return 0;
        return count.getCount();
    }


    @Override
    public long countCustomerBefore(LocalDate date) {
        return this.repository.countCustomerBefore(date);
    }

//    private LocalDate
}
