package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.BatteryData;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.PanelData;
import com.solarsmart.frontoffice.models.entities.projection.ComposantDataInfo;
import com.solarsmart.frontoffice.repositories.api.PanelDataRepository;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.api.PanelDataService;
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
public class PanelDataServiceImpl extends BaseServiceImpl<PanelData, PanelDataRepository> implements PanelDataService {
    private final ModuleService moduleService;
    public PanelDataServiceImpl(PanelDataRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public List<PanelData> list(long moduleId) {
        ModuleSolar moduleSolar = this.moduleService.get(moduleId);
        return repository.getAllByModuleId(moduleSolar.getId());
    }

    @Override
    public List<PanelData> findAllBetweenDateByModuleId(long moduleId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findAll(moduleId, startDate, endDate);
    }

    @Override
    public ExcelServiceAbstract initService(List<PanelData> data){

        return new ExcelServiceAbstract() {
            @Override
            public void writeDataTable() {
                CellStyle style = getFontContentExcel();

                // starting write on row
                int startRow = 2;

                // write content
                for (PanelData panelData : data) {
                    Row row = getSheet().createRow(startRow++);
                    int columnCount = 0;
                    createCell(row, columnCount++, panelData.getDate(), style);
                    createCell(row, columnCount++, panelData.getTension(), style);
                    createCell(row, columnCount++, panelData.getPuissance(), style);
                    createCell(row, columnCount++, panelData.getCourant(), style);
                    createCell(row, columnCount, panelData.getProduction(), style);

                }
            }
        };
    }

    @Override
    @Async
    public CompletableFuture<Void> exportExcel(OutputStream outputStream, List<PanelData> data) throws Exception {
        return CompletableFuture.runAsync(()->{
            this.export(outputStream, data);
        });
    }

    @Override
    public void export(OutputStream outputStream, List<PanelData> data) {
        ExcelServiceAbstract excelService = this.initService(data);

        String[] headers = new String[]{"Date", "Tension", "Puissance", "Courant", "Production"};

        excelService.writeTableHeaderExcel("Sheet panneaux", " Donnee panneaux", headers);

        excelService.writeDataTable();

        try {
            excelService.getWorkbook().write(outputStream);
            excelService.getWorkbook().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
