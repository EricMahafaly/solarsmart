package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.request.FilterRequest;
import com.solarsmart.frontoffice.models.dto.request.PageBuilderRequest;
import com.solarsmart.frontoffice.models.dto.response.*;
import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.Subscription;
import com.solarsmart.frontoffice.models.entities.SubscriptionPrice;
import com.solarsmart.frontoffice.models.specifications.SpecificationBuilder;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.CustomerService;
import com.solarsmart.frontoffice.services.api.base.FilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@RestController
@RequestMapping("/api/solar/customers")
@AllArgsConstructor
//@CrossOrigin("*")
@Slf4j
@Authorize
//@Authorize(roles = {"admin", "user"})
public class CustomerController {
    private final CustomerService customerService;
    private final FilterService<Customer> filterCustomerService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") long customerId){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistic")
    public ResponseEntity<?> getStatistic(){
        LocalDate date = LocalDate.now();
        int month = date.getMonth().getValue();


        LocalDate firstDayOfMonth = date.withDayOfMonth(1);

        // Dernier jour du mois
        LocalDate tomorrow = date.plusDays(1);

//        long lastMonthCount = this.customerService.getCustomerCount(month-1, date.getYear());
//        long currentMonthCount = this.customerService.getCustomerCount(month, date.getYear());

        long lastMonthCount = this.customerService.countCustomerBefore(firstDayOfMonth);
        log.info("customer count last month: {} at date: {}", lastMonthCount, firstDayOfMonth);
        long currentMonthCount = this.customerService.countCustomerBefore(tomorrow);

        CustomerStatistic customerStatistic = new CustomerStatistic(currentMonthCount, lastMonthCount);

        ApiResponse<?> response = ApiResponse
                .success()
                .body(customerStatistic)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/details")
//    @PreAuthorize()
    public ResponseEntity<?> getDetails(@PathVariable("id") long id){
        Customer customer = customerService.get(id);

        Subscription subscription = null;
//        if ()
        SubscriptionPrice subscriptionPrice = customer.getSubscription();
        if (subscriptionPrice != null) subscription = subscriptionPrice.getSubscription();

        CustomerDetailResponse detailResponse = new CustomerDetailResponse(
                customer, subscription, customer.getModule());

        ApiResponse<?> response = ApiResponse.success()
                .body(detailResponse)
                .build();
        
        return ResponseEntity.ok(response);
    }

    @GetMapping()
//    @PreAuthorize("")
//    @Authorize
    public ResponseEntity<?> listCustomers(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int pageSize,
            @RequestParam(required = false, name = "sortBy", defaultValue = "name") String sort,
            @RequestParam(required = false, name = "direction", defaultValue = "ASC") String direction){

        PageBuilderRequest request = new PageBuilderRequest();
        request.setDirection(direction);
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortBy(sort);


        Pageable pageable = request.build();

        Page<CustomerResponse> customers = this.customerService.list(pageable).map(CustomerResponse::new);

        PageResponse<CustomerResponse> pageResponse =
                new PageResponse<>(customers.getContent(), customers.getTotalElements(), customers.getTotalPages());

        ApiResponse<?> response = ApiResponse.success()
                .body(pageResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> customersFilter(
            @RequestBody FilterRequest filterRequest,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int pageSize,
            @RequestParam(required = false, name = "sortBy", defaultValue = "name") String sort,
            @RequestParam(required = false, name = "direction", defaultValue = "ASC") String direction){

        PageBuilderRequest request = new PageBuilderRequest();
        request.setDirection(direction);
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortBy(sort);


        Pageable pageable = request.build();

        SpecificationBuilder<Customer> specBuilder = new SpecificationBuilder<>();
        filterRequest.getCriteria().forEach(
                searchCriteria -> {
                    specBuilder.with(searchCriteria);
                    searchCriteria.setDataOption(filterRequest.getDataOption());
                }
        );

        Page<CustomerResponse> customers =
                this.filterCustomerService.filter(specBuilder.build(), pageable).map(CustomerResponse::new);

        PageResponse<CustomerResponse> pageResponse =
                new PageResponse<>(customers.getContent(), customers.getTotalElements(), customers.getTotalPages());

        ApiResponse<?> response = ApiResponse.success()
                .body(pageResponse)
                .build();

        return ResponseEntity.ok(response);
    }

}
