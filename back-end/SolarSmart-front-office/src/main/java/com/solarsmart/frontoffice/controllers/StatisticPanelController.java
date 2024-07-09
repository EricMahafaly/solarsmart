package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.StatisticsPanelService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/solar/statistics/panels")
@AllArgsConstructor
//@CrossOrigin("*")
@Authorize
public class StatisticPanelController implements ComposantDetailController {
    private final StatisticsPanelService statisticsPanelService;

    @GetMapping("/production/yearly/{year}/modules/{moduleId}")
    public ResponseEntity<?> getProductionUsageInYear(@PathVariable long moduleId, @PathVariable long year){
        List<FilterMonthly<Double>> filterDates = statisticsPanelService.getProductionInYear(moduleId, year);
        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/production/weekly/modules/{moduleId}")
    public ResponseEntity<?> getProductionUsageWeek(
            @PathVariable long moduleId,
            @RequestParam(name = "start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enDate){

        FilterWeekly<Double> filterDates = statisticsPanelService
                .getProductionInWeek(moduleId, startDate, enDate);

        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/production/monthly/{year}/{month}/modules/{moduleId}")
    public ResponseEntity<?> getProductionUsageWeek(
            @PathVariable long moduleId,
            @PathVariable(name = "year") long year,
            @PathVariable(name = "month") int month){

        List<FilterWeekly<Double>> filterDates = statisticsPanelService.getProductionWeeklyInMonth(moduleId, month, year);

        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/production/monthly/{year}/{month}/weekly/{week}/modules/{moduleId}")
    public ResponseEntity<?> getProductionWeeklyInMonth(
            @PathVariable long moduleId,
            @PathVariable(name = "year") long year,
            @PathVariable(name = "month") int month,
            @PathVariable int week){

        FilterWeekly<Double> filterDates = statisticsPanelService.getProductionInWeek(moduleId, week, month, year);

        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/production/modules/{moduleId}")
    public ResponseEntity<?> findProductionBetweenTime(
            @PathVariable long moduleId,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){

        double production = statisticsPanelService
                .findProductionBetweenTimeAndModuleId(moduleId, startTime, endTime, date);

        ApiResponse<?> response = ApiResponse.success()
                .body(production)
                .build();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/productions/modules/{moduleId}")
    public ResponseEntity<?> getProductionsByDate(
            @PathVariable long moduleId,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){

        List<ComposantProjection> consommations = statisticsPanelService
                .getProductionBetweenDateByModuleId(moduleId, date, startTime, endTime);
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
        ComposantDataInfo data = this.statisticsPanelService.findByTimeAndModule(moduleId, date);

        ApiResponse<?> response = ApiResponse.success()
                .body(data)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter/modules/{moduleId}")
    @Override
    public ResponseEntity<?> getAllDetailByDate(
            @PathVariable(name = "moduleId") long moduleId,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime endTime) {

        List<ComposantDataInfo> data = this.statisticsPanelService.findAllBetweenTimeByModule(moduleId, date, startTime, endTime);

        ApiResponse<?> response = ApiResponse
                .success()
                .body(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
