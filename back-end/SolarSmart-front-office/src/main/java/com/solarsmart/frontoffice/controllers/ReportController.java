package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.request.FilterRequest;
import com.solarsmart.frontoffice.models.dto.request.PageBuilderRequest;
import com.solarsmart.frontoffice.models.dto.request.ReportCommentRequest;
import com.solarsmart.frontoffice.models.dto.request.ReportRegistration;
import com.solarsmart.frontoffice.models.dto.response.*;
import com.solarsmart.frontoffice.models.entities.*;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.models.specifications.SpecificationBuilder;
import com.solarsmart.frontoffice.services.api.*;
import com.solarsmart.frontoffice.services.api.base.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/solar/reports")
@RequiredArgsConstructor
@Slf4j
public class ReportController {

    private final ReportService reportService;
    private final CustomerService customerService;
    private final ReportStateService reportStateService;
    private final ReportCommentService reportCommentService;
    private final AdminService adminService;
    private final FilterService<Report> reportFilterService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReportRegistration reportRegistration){

        Customer customer = customerService.get(reportRegistration.getCustomerId());
        ReportState reportState = reportStateService.findByState(ReportStateEnum.IN_PROGRESS)
                .orElseThrow(() -> new DomainException("cette état n'existe pas"));

        Report report = reportRegistration.toModel();
        report.setState(reportState);

        report.setCustomer(customer);

        reportService.create(report);

        ApiResponse<?> response = ApiResponse
                .success()
                .message("insertion est un success")
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int pageSize,
            @RequestParam(required = false, name = "sortBy", defaultValue = "name") String sort,
            @RequestParam(required = false, name = "direction", defaultValue = "ASC") String direction,
            @PathVariable(name = "id") long adminId){

        PageBuilderRequest request = new PageBuilderRequest();
        request.setDirection(direction);
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortBy(sort);


        Pageable pageable = request.build();

        Page<ReportListResponse> report = this.reportService.getAllByAdmin(adminId, pageable).map(ReportListResponse::new);

        PageResponse<ReportListResponse> pageResponse =
                new PageResponse<>(report.getContent(), report.getTotalElements(), report.getTotalPages());

        ApiResponse<?> response = ApiResponse.success()
                .body(pageResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/states")
    public ResponseEntity<?> listStates(){
        List<ReportState> reportStates = this.reportStateService.list();

        List<ReportStateListResponse> responses = reportStates
                .stream()
                .map(ReportStateListResponse::new)
                .collect(Collectors.toList());

        ApiResponse<?> response = ApiResponse
                .success()
                .body(responses)
                .build();

        return ResponseEntity.ok(response);
    }


    @PostMapping("/filter")
    public ResponseEntity<?> reportsFilter(
            @RequestBody List<FilterRequest> filterRequests,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int pageSize,
            @RequestParam(required = false, name = "sortBy", defaultValue = "name") String sort,
            @RequestParam(required = false, name = "direction", defaultValue = "ASC") String direction){

        log.info("filter: {}", filterRequests);

        PageBuilderRequest request = new PageBuilderRequest();
        request.setDirection(direction);
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortBy(sort);

        Pageable pageable = request.build();

        SpecificationBuilder<Report> specBuilder = new SpecificationBuilder<>();

        filterRequests.forEach(filterRequest -> {
            filterRequest.getCriteria().forEach(
                    searchCriteria -> {
                        specBuilder.with(searchCriteria);
                        searchCriteria.setDataOption(filterRequest.getDataOption());
                    }
            );
        });

        Page<ReportListResponse> reports =
                this.reportFilterService.filter(specBuilder.build(), pageable).map(ReportListResponse::new);

        log.info("page responses: {}", reports);

        PageResponse<ReportListResponse> pageResponse =
                new PageResponse<>(reports.getContent(), reports.getTotalElements(), reports.getTotalPages());

        ApiResponse<?> response = ApiResponse.success()
                .body(pageResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/close")
    public ResponseEntity<?> closeReport(@PathVariable Long id){
        Report report = this.reportService.get(id);

        ReportState state = this.reportStateService.findByState(ReportStateEnum.CLOSED).get();

        report.setState(state);
        report.setClosedDate(LocalDateTime.now());

        this.reportService.create(report);

        ApiResponse<?> response = ApiResponse
                .success()
                .message("This report is resolved")
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable long id){
        Report report = this.reportService.get(id);

        ReportDetail reportDetail = new ReportDetail(report);

        ApiResponse<?> response = ApiResponse
                .success()
                .body(reportDetail)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> createComments(@PathVariable long id, @RequestBody ReportCommentRequest commentRequest){
        Report report = this.reportService.get(id);

        if (report.getAdmin() == null){
            Admin admin = this.adminService.get(commentRequest.getAdminId());
            report.setAdmin(admin);
            report = reportService.create(report);
        }

        ReportComment reportComment = commentRequest.toModel();
        reportComment.setReport(report);

        this.reportCommentService.create(reportComment);

        ApiResponse<?> response = ApiResponse
                .success()
                .message("Comment added in report")
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/unsolved/count")
    public ResponseEntity<?> countUnsolved(){

        long count = this.reportService.countReportUnsolved();

        ApiResponse<?> response = ApiResponse
                .success()
                .body(count)
                .build();

        return ResponseEntity.ok(response);
    }
}
