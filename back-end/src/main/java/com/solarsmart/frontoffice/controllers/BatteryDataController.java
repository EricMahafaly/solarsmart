package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.request.BatteryDataRegistration;
import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.BatteryDataResponse;
import com.solarsmart.frontoffice.models.dto.response.PageResponse;
import com.solarsmart.frontoffice.models.entities.Battery;
import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.BatteryDataService;
import com.solarsmart.frontoffice.services.api.BatteryService;
import com.solarsmart.frontoffice.services.api.ReferenceBatteryService;
import com.solarsmart.frontoffice.services.api.RelayBatterieService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/solar/batteries/data")
@AllArgsConstructor
@Slf4j
public class BatteryDataController {

    private final BatteryDataService batteryDataService;
    private final BatteryService batteryService;

    @GetMapping("/modules/{idModule}")
    @Authorize
    public ResponseEntity<?> getDataByIdModule(
            @PathVariable(value = "idModule") long idModule,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize){

        Page<BatteryDataResponse> data = this.batteryDataService
                .getByModuleId(idModule, PageRequest.of(pageNumber, pageSize, Sort.by("date").ascending()))
                .map(BatteryDataResponse::toPublic);

        ApiResponse<?> response = ApiResponse.success()
                .body(new PageResponse<>(data.getContent(), data.getTotalElements(), data.getTotalPages()))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter/modules/{idModule}")
    @Authorize
    public ResponseEntity<?> getDataByIdModuleAndBetweenTime(
            @PathVariable(value = "idModule") long idModule,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime){

        List<com.solarsmart.frontoffice.models.entities.BatteryData> data = this.batteryDataService.findAllBetweenTimeByModuleId(idModule, date, startTime, endTime);

        List<BatteryDataResponse> dataPublics = data
                .stream()
                .map(BatteryDataResponse::new)
                .toList();

//        messagingTemplate.convertAndSend("/topic/batteryData", dataPublics);

        ApiResponse<?> response = ApiResponse.success()
                .body(dataPublics)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/export/excel/all")
    @Authorize
    public CompletableFuture<Void> exportToExcel(HttpServletResponse response) throws Exception {

        return CompletableFuture.runAsync(() ->{
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=battery_data_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);


            try {
                this.batteryDataService.export(response.getOutputStream(), this.batteryDataService.list(Sort.by("date")));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


//    @GetMapping("/export/excel/all")
//    @Authorize
//    public CompletableFuture<Void> exportToExcel(HttpServletResponse response) throws Exception {
//
//        return CompletableFuture.runAsync(() ->{
//            response.setContentType("application/octet-stream");
//            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//            String currentDateTime = dateFormatter.format(new Date());
//
//            String headerKey = "Content-Disposition";
//            String headerValue = "attachment; filename=battery_data_" + currentDateTime + ".xlsx";
//            response.setHeader(headerKey, headerValue);
//
//
//            try {
//                this.batteryDataService.export(response.getOutputStream(), this.batteryDataService.list(Sort.by("date")));
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }

    @PostMapping("/modules/{idModule}")
//    @PostAuthorize()
    public ResponseEntity<?> addData(@PathVariable long idModule, @RequestBody BatteryDataRegistration batteryDataModel){

        log.info("data = {}", batteryDataModel);

        if (batteryDataModel.getDate() == null) batteryDataModel.setDate(LocalDateTime.now());

        Battery battery = batteryService.getByModule(idModule);

        BatteryData batteryData = batteryDataModel.toModel(battery);

        BatteryData newData = batteryDataService.create(batteryData);


        BatteryDataResponse batteryDataResponse = BatteryDataResponse.toPublic(newData);


        ApiResponse.ApiResponseBuilder<BatteryDataResponse> builder = ApiResponse.builder();

//        if (newData != null){
//
//            relayBatterieService.relayed(battery, batteryData.getCourant());
//
//            BatteryDataResponse batteryDataResponse = BatteryDataResponse.toPublic(newData);
//
//            referenceBatteryService.handleIfConditionTrue(idModule, newData.getDate());
//
//            builder.isSuccess(true)
//                    .body(batteryDataResponse)
//                    .message("insertion est un succès");
//        }else{
//
//            builder.isSuccess(false)
//                    .message("insertion est un échec");
//        }

        builder.isSuccess(true)
                .body(batteryDataResponse)
                .message("insertion is a success");



        return ResponseEntity.ok(builder.build());
    }

}
