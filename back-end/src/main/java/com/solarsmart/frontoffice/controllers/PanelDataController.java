package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.request.PanneauDataRegistration;
import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.PanelDataResponse;
import com.solarsmart.frontoffice.models.entities.Panel;
import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.PanelDataService;
import com.solarsmart.frontoffice.services.api.PanelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/solar/panels/data")
@AllArgsConstructor
@Slf4j
@Authorize
//@CrossOrigin({"http://localhost:4200"})
public class PanelDataController {

    private final PanelDataService panelDataService;
    private final PanelService panelService;

    @PostMapping("/modules/{idModule}")
    public ResponseEntity<?> addData(@PathVariable long idModule, @RequestBody PanneauDataRegistration panneauDataRegistration){
        if (panneauDataRegistration.getDate() == null) panneauDataRegistration.setDate(LocalDateTime.now());
        PanelData panelData = panneauDataRegistration.toModel();
        Panel panel = panelService.getByModuleId(idModule);

        panelData.setPanel(panel);

        PanelData newData = panelDataService.create(panelData);

        ApiResponse.ApiResponseBuilder<PanelDataResponse> builder = ApiResponse.builder();

        if (newData != null) {
            builder.body(PanelDataResponse.toPublic(panelData))
                    .isSuccess(true)
                    .message("Insertion is a success");
        }else{
            builder.message("Insertion is a failure")
                    .isSuccess(false);
        }


        return ResponseEntity.ok(builder.build());
    }
    @GetMapping("/modules/{idModule}")
    public ResponseEntity<?> getData(@PathVariable long idModule){
        List<PanelDataResponse> panelDataResponses = PanelDataResponse.toPublic(this.panelDataService.list(idModule));
        ApiResponse<?> response = ApiResponse.success()
                .body(panelDataResponses)
                .message("Retrieve data for panel is a successful")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/export/excel/all")
    public CompletableFuture<Void> exportToExcel(HttpServletResponse response) {

        log.info("export panel data in progress...");

        return CompletableFuture.runAsync(()-> {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=battery_data_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);


            try {
                this.panelDataService.export(response.getOutputStream(), this.panelDataService.list(Sort.by("date")));
            } catch (Exception e) {
                log.error("error on export panel data to excel: {}", e.getMessage());
                throw new RuntimeException(e);
            }

            log.info("export panel data succès");
        });
    }

//    @GetMapping("/export/excel/all")
//    public ResponseEntity<?> exportExcel(){
//
//    }
}
