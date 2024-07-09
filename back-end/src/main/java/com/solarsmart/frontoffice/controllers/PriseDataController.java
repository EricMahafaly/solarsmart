package com.solarsmart.frontoffice.controllers;


import com.solarsmart.frontoffice.models.dto.request.PriseDataRegistration;
import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.PriseDataResponse;
import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.models.entities.PriseData;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.PriseDataService;
import com.solarsmart.frontoffice.services.api.PriseService;
import com.solarsmart.frontoffice.services.api.ReferencePriseService;
import com.solarsmart.frontoffice.services.api.RelayPriseService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/solar/prises/data")
@AllArgsConstructor
public class PriseDataController {
    private final PriseService priseService;
    private final PriseDataService priseDataService;

    private final RelayPriseService relayPriseService;

    private final ReferencePriseService referencePriseService;

    @PostMapping("/modules/{idModule}")
    public ResponseEntity<?> addData( @PathVariable long idModule ,@RequestBody PriseDataRegistration priseDataRegistration){
        if (priseDataRegistration.getDate() == null) priseDataRegistration.setDate(LocalDateTime.now());

        Prise prise = priseService.getByModuleId(idModule);

        PriseData priseData = priseDataRegistration.toModel();

        priseData.setPrise(prise);

        ApiResponse.ApiResponseBuilder<PriseDataResponse> builder = ApiResponse.builder();

        PriseData newData = priseDataService.create(priseData);

        if (newData != null){
            relayPriseService.relayed(prise, newData.getCourant());
            PriseDataResponse batteryDataPublic = PriseDataResponse.toPublic(newData);

            referencePriseService.handleIfConditionTrue(idModule, newData.getDate());

            builder.isSuccess(true)
                    .body(batteryDataPublic)
                    .message("Insertion is a success");
        }else{

            builder.isSuccess(false)
                    .message("Insertion is a failure");
        }


        return ResponseEntity.ok(builder.build());
    }

    @GetMapping("/modules/{idModule}")
    @Authorize
    public ResponseEntity<?> getData(@PathVariable long idModule){
        List<PriseDataResponse> panelDataPublics = PriseDataResponse.toPublic(this.priseDataService.list(idModule));
        ApiResponse<?> response = ApiResponse.success()
                .body(panelDataPublics)
                .message("Retrieve data for  is a successful")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/export/excel/all")
    @Authorize
    public CompletableFuture<Void> exportToExcel(HttpServletResponse response) {

        return CompletableFuture.runAsync(()->{
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=prise_data_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);


            try {
                this.priseDataService.export(response.getOutputStream(), this.priseDataService.list(Sort.by(Sort.Direction.DESC,"date")));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
