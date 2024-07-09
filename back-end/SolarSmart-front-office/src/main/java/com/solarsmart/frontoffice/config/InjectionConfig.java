package com.solarsmart.frontoffice.config;

import com.solarsmart.frontoffice.models.entities.*;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.base.SearchRepository;
import com.solarsmart.frontoffice.repositories.base.SearchRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.*;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import com.solarsmart.frontoffice.services.api.base.FilterService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import com.solarsmart.frontoffice.services.implementation.base.FilterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InjectionConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SearchRepository<Customer> searchCustomerRepository(JpaCustomerRepository jpaCustomerRepository){
        return new SearchRepositoryImpl<>(jpaCustomerRepository);
    }


    @Bean
    public SearchRepository<Rating> searchRatingRepository(JpaRatingRepository jpaRatingRepository){
        return new SearchRepositoryImpl<>(jpaRatingRepository);
    }

    @Bean
    public SearchRepository<Report> searchReportRepository(JpaReportRepository jpaReportRepository){
        return new SearchRepositoryImpl<>(jpaReportRepository);
    }


    @Bean(name = "moduleAdditionalInfoDetailRepository")
    public BaseRepository<ModuleAdditionalInfoDetail> moduleAdditionalInfoDetailRepository(
            JpaModuleAdditionalInfoDetailRepository jpaModuleAdditionalInfoDetailRepository){
        return new BaseRepositoryImpl<>(jpaModuleAdditionalInfoDetailRepository);
    }

    @Bean(name = "moduleAdditionalInfoDetailService")
    public BaseService<ModuleAdditionalInfoDetail> moduleAdditionalInfoDetailService(
            BaseRepository<ModuleAdditionalInfoDetail> moduleAdditionalInfoDetailRepository){
        return new BaseServiceImpl<>(moduleAdditionalInfoDetailRepository);
    }

    @Bean
    public FilterService<Customer> filterCustomer(SearchRepository<Customer> searchRepository){
        return new FilterServiceImpl<>(searchRepository);
    }

    @Bean
    public FilterService<Rating> filterRating(SearchRepository<Rating> searchRepository){
        return new FilterServiceImpl<>(searchRepository);
    }

    @Bean
    public FilterService<Report> filterReport(SearchRepository<Report> searchRepository){
        return new FilterServiceImpl<>(searchRepository);
    }
}
