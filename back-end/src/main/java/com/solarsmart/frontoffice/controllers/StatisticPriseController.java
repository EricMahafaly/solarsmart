package com.solarsmart.frontoffice.controllers;


import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.StatisticsPriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/solar/statistics/prises")
@RequiredArgsConstructor
//@CrossOrigin("*")
@Authorize
//@RequiredArgsConstructor
public class StatisticPriseController implements ComposantDetailController {
    private final StatisticsPriseService statisticsPriseService;
    @GetMapping("/consommation/monthly/{year}/{month}/modules/{moduleId}")
    public ResponseEntity<?> getConsommationWeekly(@PathVariable long moduleId,
                                                 @PathVariable long year,
                                                 @PathVariable int month){
        List<FilterWeekly<Double>> filterDates = statisticsPriseService.getConsommationWeeklyInMonth(moduleId, month, year);
        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/consommation/yearly/{year}/modules/{moduleId}")
    public ResponseEntity<?> getConsommationInYear(@PathVariable long moduleId,
                                                   @PathVariable long year){
        List<FilterMonthly<Double>> filterDates = statisticsPriseService.getConsommationInYear(moduleId, year);

        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/consommation/modules/{moduleId}")
    public ResponseEntity<?> findConsommationBetweenTime(
            @PathVariable long moduleId,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){

        double tension = statisticsPriseService.getConsommationBetweenTimeAndModuleId(moduleId, startTime, endTime, date);
        ApiResponse<?> response = ApiResponse.success()
                .body(tension)
                .build();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/consommations/modules/{moduleId}")
    public ResponseEntity<?> getConsommationsByDate(
            @PathVariable(name = "moduleId") long moduleId,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime endTime){

        List<ComposantProjection> consommations = statisticsPriseService
                .getConsommationsBetweenDateByModuleId(moduleId, date, startTime, endTime);
        ApiResponse<?> response = ApiResponse.success()
                .body(consommations)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/modules/{moduleId}")
    @Override
    public ResponseEntity<?> findDetailByTime(
            @PathVariable long moduleId,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        ComposantDataInfo data = this.statisticsPriseService.findByTimeAndModule(moduleId, date);

        ApiResponse<?> response = ApiResponse.success()
                .body(data)
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/filter/modules/{moduleId}")
    public ResponseEntity<?> getAllDetailByDate(
            @PathVariable(name = "moduleId") long moduleId,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime endTime) {
        List<ComposantDataInfo> data = this.statisticsPriseService.findAllBetweenTimeByModule(moduleId, date, startTime, endTime);

        ApiResponse<?> response = ApiResponse
                .success()
                .body(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
