package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.dto.response.CustomerStatistic;
import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.projection.CustomerCount;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
//@Primary
public interface JpaCustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByModule_Id(Long id);

    @Query(nativeQuery = true, value = "select vccm.count as count, vccm.month as month, vccm.year as year" +
            " from v_customer_count_monthly vccm where vccm.month = ?1 and vccm.year =?2")
    Optional<CustomerCount> findCustomerCountByMonthAndYear(int month, long year);

    @Query("select count(c) from Customer c where Date(c.registrationDate) < ?1")
    long countByRegistrationDateBefore(LocalDate date);

//    long countCustomerByRegistrationDateBefore

    @Query(nativeQuery = true, value = "select vccm.count as count, vccm.month as month, vccm.year as year" +
            " from v_customer_count_monthly vccm where vccm.year =?2")
    List<CustomerCount> findClientsCountByMonthAndYear(long year);
}
