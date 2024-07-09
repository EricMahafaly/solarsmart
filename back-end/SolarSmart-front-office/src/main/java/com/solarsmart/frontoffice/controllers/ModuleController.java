package com.solarsmart.frontoffice.controllers;

import com.google.zxing.WriterException;
import com.solarsmart.frontoffice.models.dto.request.*;
import com.solarsmart.frontoffice.models.dto.response.*;
import com.solarsmart.frontoffice.models.entities.*;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import com.solarsmart.frontoffice.services.api.*;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/solar/modules")
@AllArgsConstructor
@Slf4j
public class ModuleController {
    private final ModuleService moduleService;
    private final PanelService panelService;
    private final BatteryService batteryService;
    private final RelaisService relaisService;
    private final ReferencePriseService referencePriseService;
    private final ReferenceBatteryService referenceBatteryService;
    private final BaseService<ModuleAdditionalInfo> moduleInfoService;

    @PutMapping("/{id}")
    @Transactional
    @Authorize
    public ResponseEntity<?> update(@RequestBody ModuleSolarRequest moduleRegistration, @PathVariable long id){

        log.info("module registration: {}", moduleRegistration);

        ModuleSolar module = moduleService.get(id);

        Battery existingBattery = batteryService.getByModule(id);

        Battery battery = moduleRegistration.extractBattery();
        battery.setModule(module);
        battery.setId(existingBattery.getId());
        battery.setRelaisState(existingBattery.getRelaisState());


        Panel existingPanel = panelService.getByModuleId(id);

        Panel panel = moduleRegistration.extractPanel();
        panel.setModule(module);
        panel.setId(existingPanel.getId());
        panel.setRelaisState(existingPanel.getRelaisState());

        batteryService.create(battery);
        panelService.create(panel);

        List<ModuleAdditionalInfo> moduleAdditionalInfos = moduleRegistration.extractOtherInfo();
        moduleAdditionalInfos.forEach(moduleInfo -> moduleInfo.setModule(module));

        this.moduleInfoService.createMany(moduleAdditionalInfos);

        ApiResponse<?> response = ApiResponse.success()
                .message("Modification is a success")
                .build();

        return ResponseEntity.ok(response);
    }


    @PostMapping
    @Transactional
    @Authorize
    public ResponseEntity<?> create(@RequestBody ModuleSolarRequest moduleSolarRequest){

        log.info("module register: {}", moduleSolarRequest);

        ModuleSolar moduleSolar = moduleSolarRequest.extractModuleSolar();
        RelaisState relaisState = relaisService.getByState(RelayStateEnum.HIGH);

        Panel panel = moduleSolarRequest.extractPanel();
        panel.setModule(moduleSolar);
        panel.setRelaisState(relaisState);

        Battery battery = moduleSolarRequest.extractBattery();
        battery.setModule(moduleSolar);
        battery.setRelaisState(relaisState);

        Prise prise = new Prise();
        prise.setRelaisState(relaisState);
        prise.setModule(moduleSolar);

        List<ModuleAdditionalInfo> moduleAdditionalInfos = moduleSolarRequest.extractOtherInfo();
        moduleAdditionalInfos.forEach(moduleInfo -> moduleInfo.setModule(moduleSolar));

        moduleSolar.setPanel(panel);
        moduleSolar.setBattery(battery);
        moduleSolar.setPrise(prise);
        moduleSolar.setOtherInfo(moduleAdditionalInfos);


        ModuleSolar newModuleSolar = moduleService.create(moduleSolar);

        referencePriseService.create(new ReferencePrise(moduleSolar));
        referenceBatteryService.create(new ReferenceBattery(moduleSolar));

//        panelService.create(panel);
//        batteryService.create(battery);
//        priseService.create(prise);

        ApiResponse<?> response = ApiResponse.success()
                .body(new ModuleResponse(newModuleSolar))
                .message("Module creation is a success")
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}/qrcode")
    public ResponseEntity<?> generateQRCode(@PathVariable long id) throws IOException, WriterException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        this.moduleService.toQrCode(id, outputStream);

        byte[] imageBytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(new ByteArrayResource(imageBytes), headers, HttpStatus.OK);
    }

    @GetMapping()
    @Authorize
    public ResponseEntity<?> listModules(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int pageSize,
            @RequestParam(required = false, name = "sortBy", defaultValue = "createdDate") String sort,
            @RequestParam(required = false, name = "direction", defaultValue = "ASC") String direction){

        PageBuilderRequest request = new PageBuilderRequest();
        request.setDirection(direction);
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortBy(sort);


        Pageable pageable = request.build();

        Page<ModuleResponse> pages = this.moduleService.list(pageable).map(ModuleResponse::new);

        PageResponse<ModuleResponse> moduleResponse = new PageResponse<>(pages.getContent(),
                pages.getTotalElements(),
                pages.getTotalPages());

        ApiResponse<?> response = ApiResponse.success()
                .body(moduleResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/unused")
    @Authorize
    public ResponseEntity<?> modulesUnused(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int pageSize){

        if(page < 1) page = 1;
        if(pageSize < 1) pageSize = 10;
        page = page - 1;

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        Page<ModuleResponse> modulePublics = this.moduleService.getModulesUnused(pageable).map(ModuleResponse::new);

        ApiResponse<?> response = ApiResponse.success()
                .body(modulePublics)
                .build();

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}/info")
    @Authorize
    public ResponseEntity<?> info(@PathVariable long id){
        ModuleSolar moduleSolar = this.moduleService.get(id);
        ModuleDetailResponse moduleDetailResponse = new ModuleDetailResponse(moduleSolar);

        ApiResponse<?> response = ApiResponse.success()
                .body(moduleDetailResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        this.moduleService.delete(id);

        ApiResponse<?> response = ApiResponse.builder()
                .message("The action was completed successfully")
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/statistic")
    public ResponseEntity<?> getStatistic(){
        List<ModuleSolar> moduleSolar = this.moduleService.list();

        ModuleSolarStatistic moduleSolarStatistic = new ModuleSolarStatistic(moduleSolar);

        ApiResponse<?> response = ApiResponse.success()
                .body(moduleSolarStatistic)
                .build();

        return ResponseEntity.ok(response);
    }



    @GetMapping("/{id}")
    @Authorize
    public ResponseEntity<?> getById(@PathVariable long id){
        ModuleSolar moduleSolar = moduleService.get(id);

        ModuleResponse moduleResponse = new ModuleResponse(moduleSolar);
        ApiResponse<?> response = ApiResponse.success()
                .body(moduleResponse)
                .message("Insertion is a success")
                .build();

        return ResponseEntity.ok(response);
    }
}
