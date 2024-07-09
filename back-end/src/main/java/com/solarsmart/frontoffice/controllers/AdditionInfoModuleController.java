package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.entities.ModuleAdditionalInfoDetail;
import com.solarsmart.frontoffice.services.api.ModuleAdditionalInfoService;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solar/modules/additional-info")
@Slf4j
public class AdditionInfoModuleController {

    private final ModuleAdditionalInfoService moduleAdditionalInfoService;
    @Qualifier("moduleAdditionalInfoDetailService")
    private final BaseService<ModuleAdditionalInfoDetail> moduleAdditionalInfoDetailService;

    public AdditionInfoModuleController( ModuleAdditionalInfoService moduleAdditionalInfoService, BaseService<ModuleAdditionalInfoDetail> moduleAdditionalInfoDetailService) {
        this.moduleAdditionalInfoService = moduleAdditionalInfoService;
        this.moduleAdditionalInfoDetailService = moduleAdditionalInfoDetailService;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable long id){

        this.moduleAdditionalInfoService.get(id);
//        ModuleAdditionalInfo moduleAdditionalInfos = registration.toModel();

        this.moduleAdditionalInfoService.delete(id);

        ApiResponse<?> response = ApiResponse
                .success()
                .message("suppression est un succès")
                .build();

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/details/{id}")
    @Transactional
    public ResponseEntity<?> deleteDetail(
            @PathVariable long id){

        this.moduleAdditionalInfoDetailService.get(id);

        this.moduleAdditionalInfoDetailService.delete(id);

        ApiResponse<?> response = ApiResponse
                .success()
//                .message("suppression est un succès")
                .message("deletion is successful")
                .build();

        return ResponseEntity.ok(response);

    }


}
