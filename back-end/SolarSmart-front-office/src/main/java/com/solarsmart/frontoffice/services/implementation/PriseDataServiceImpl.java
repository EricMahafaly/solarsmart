package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.models.entities.PriseData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.repositories.api.PriseDataRepository;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.api.PriseDataService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import com.solarsmart.frontoffice.services.implementation.base.ExcelServiceAbstract;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
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
public class PriseDataServiceImpl extends BaseServiceImpl<PriseData, PriseDataRepository> implements PriseDataService {
    private final ModuleService moduleService;

    public PriseDataServiceImpl(PriseDataRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public List<PriseData> list(long moduleId) {
        this.moduleService.get(moduleId);
        return this.repository.getAllByModuleId(moduleId);
    }

    @Override
    public List<PriseData> findAllBetweenDateByModuleId(long moduleId, LocalDateTime date1, LocalDateTime date2) {
        return repository.findAll(moduleId, date1, date2);
    }

    @Override
    @Async
    public CompletableFuture<Void> exportExcel(OutputStream outputStream, List<PriseData> data) throws Exception {
        return CompletableFuture.runAsync(()-> {
            this.export(outputStream, data);

        });
    }

    @Override
    public void export(OutputStream outputStream, List<PriseData> data) {
        ExcelServiceAbstract excelService = this.initService(data);

        String[] headers = new String[]{"Date", "Tension", "Puissance", "Courant", "Consommation"};

        excelService.writeTableHeaderExcel("Sheet panneaux", " Donnee panneaux", headers);

        excelService.writeDataTable();

        try {
            excelService.getWorkbook().write(outputStream);
            excelService.getWorkbook().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
//    @Async
    public ExcelServiceAbstract initService(List<PriseData> data) {
        return new ExcelServiceAbstract() {
            @Override
            public void writeDataTable() {
                CellStyle style = getFontContentExcel();

                // starting write on row
                int startRow = 2;

                // write content
                for (PriseData priseData : data) {
                    Row row = getSheet().createRow(startRow++);
                    int columnCount = 0;
                    createCell(row, columnCount++, priseData.getDate(), style);
                    createCell(row, columnCount++, priseData.getTension(), style);
                    createCell(row, columnCount++, priseData.getPuissance(), style);
                    createCell(row, columnCount++, priseData.getCourant(), style);
                    createCell(row, columnCount++, priseData.getConsommation(), style);

                }
            }
        };
    }
}
