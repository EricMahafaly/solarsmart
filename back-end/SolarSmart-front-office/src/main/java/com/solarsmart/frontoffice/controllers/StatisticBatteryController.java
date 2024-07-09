package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.ComposantDetailsResponse;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterMonthly;
import com.solarsmart.frontoffice.models.dto.response.filter.FilterWeekly;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.models.entities.projection.ComposantProjection;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.StatisticsBatteryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/solar/statistics/batteries")
//@CrossOrigin("*")
@AllArgsConstructor
@Slf4j
@Authorize
public class StatisticBatteryController implements ComposantDetailController {
    private final StatisticsBatteryService statisticsBatteryService;


    @GetMapping("/time-usage/yearly/{year}/modules/{moduleId}")
    public ResponseEntity<?> getTimeUsageInYear(
            @PathVariable long moduleId,
            @PathVariable long year){

        List<FilterMonthly<Double>> filterDates = statisticsBatteryService.getTimeUsageInYear(moduleId, year);

        log.info("retrieve time usage monthly by year: {} and module id: {}", year, moduleId);
        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/time-usage/monthly/{year}/{month}/modules/{moduleId}")
    public ResponseEntity<?> getTimeUsageWeeklyInMonth(
            @PathVariable long moduleId,
            @PathVariable long year,
            @PathVariable int month){
        
        List<FilterWeekly<Double>> filterDates = statisticsBatteryService.getTimeUsageInMonth(moduleId, month, year);
        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/time-usage/monthly/{year}/{month}/weekly/{week}/modules/{moduleId}")
    public ResponseEntity<?> getTimeUsageWeeklyInMonth(
            @PathVariable long moduleId,
            @PathVariable(name = "year") long year,
            @PathVariable(name = "month") int month,
            @PathVariable int week){

        FilterWeekly<Double> filterDates = statisticsBatteryService.getTimeUsageWeeklyInMonth(moduleId, week, month, year);

        ApiResponse<?> response = ApiResponse.success()
                .body(filterDates)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/energies/modules/{moduleId}")
    public ResponseEntity<?> getAllEnergyByDate(
            @PathVariable(name = "moduleId") long moduleId,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime endTime){

        List<ComposantProjection> energies = statisticsBatteryService
                .getEnergiesBetweenDateByModuleId(moduleId, date, startTime, endTime);
        ApiResponse<?> response = ApiResponse.success()
                .body(energies)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/energy/modules/{moduleId}")
    public ResponseEntity<?> sumEnergyBetweenTime(
            @PathVariable long moduleId,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){

        double tension = statisticsBatteryService.getEnergyBetweenTimeAndModuleId(moduleId, startTime, endTime, date);

        log.info("tension between {} and {} is: {}", startTime, endTime, tension);
        ApiResponse<?> response = ApiResponse.success()
                .body(tension)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/modules/{moduleId}")
    @Override
    public ResponseEntity<?> findDetailByTime(
            @PathVariable long moduleId,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        log.info("detail at date: {} and by module with id: {}", date, moduleId);
        ComposantDataInfo info = this.statisticsBatteryService.findByTimeAndModule(moduleId, date);

        ComposantDetailsResponse data = new ComposantDetailsResponse(info);

        data.setDate(date);

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

        List<ComposantDataInfo> data = this.statisticsBatteryService.findAllBetweenTimeByModule(moduleId, date, startTime, endTime);

//        List<ComposantDetailsResponse> data = composantDataInfos
//                .stream()
//                .map(ComposantDetailsResponse::new)
//                .collect(Collectors.toList());

        ApiResponse<?> response = ApiResponse
                .success()
                .body(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
