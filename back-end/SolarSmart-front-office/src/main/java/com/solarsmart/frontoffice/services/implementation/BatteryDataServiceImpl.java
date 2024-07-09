package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.repositories.api.BatteryDataRepository;
import com.solarsmart.frontoffice.services.api.BatteryDataService;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import com.solarsmart.frontoffice.services.implementation.base.ExcelServiceAbstract;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class BatteryDataServiceImpl extends BaseServiceImpl<BatteryData, BatteryDataRepository> implements BatteryDataService {
    private final ModuleService moduleService;
//    private

    public BatteryDataServiceImpl(BatteryDataRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public List<BatteryData> getByModuleId(Long idModule) {
        this.moduleService.get(idModule);
        return this.repository.getAllByModuleId(idModule);
    }

    @Override
    public Page<BatteryData> getByModuleId(Long idModules, Pageable pageable) {
        return repository.findAllByModuleId(idModules, pageable);
    }

    @Override
    public List<BatteryData> findAllBetweenDateByModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return repository.findAll(moduleId, date1, date2);
    }

    @Override
    public List<BatteryData> findAllBetweenTimeByModuleId(long moduleId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDate = date.atTime(startTime);
        LocalDateTime endDate = date.atTime(endTime);
        return this.findAllBetweenDateByModuleId(moduleId, startDate, endDate);
    }


    @Override
    public ExcelServiceAbstract initService(List<BatteryData> data){

        return new ExcelServiceAbstract() {
            @Override
            public void writeDataTable() {
                CellStyle style = getFontContentExcel();

                // starting write on row
                int startRow = 2;

                // write content
                for (BatteryData batteryData : data) {
                    Row row = getSheet().createRow(startRow++);
                    int columnCount = 0;
                    createCell(row, columnCount++, batteryData.getDate(), style);
                    createCell(row, columnCount++, batteryData.getTension(), style);
                    createCell(row, columnCount++, batteryData.getPuissance(), style);
                    createCell(row, columnCount++, batteryData.getCourant(), style);
                    createCell(row, columnCount++, batteryData.getPourcentage(), style);
                    createCell(row, columnCount++, batteryData.getEnergy(), style);

                }
            }
        };
    }



    @Override
    @Async
    public CompletableFuture<Void> exportExcel(OutputStream outputStream, List<BatteryData> data) throws Exception {
        return CompletableFuture.runAsync(() -> {
            this.export(outputStream, data);
        });

    }

    @Override
    public void export(OutputStream outputStream, List<BatteryData> data) {
        ExcelServiceAbstract excelService = this.initService(data);

        String[] headers = new String[]{"Date", "Tension", "Puissance", "Courant", "Pourcentage", "Energie"};

        excelService.writeTableHeaderExcel("Sheet battery data", " Donnee battery", headers);

        excelService.writeDataTable();

        try {
            excelService.getWorkbook().write(outputStream);
//            excelService.getWorkbook().close();
            excelService.getWorkbook().dispose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
