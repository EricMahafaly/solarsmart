package com.solarsmart.frontoffice.services.api.base;

import com.solarsmart.frontoffice.services.implementation.base.ExcelServiceAbstract;
import org.springframework.scheduling.annotation.Async;

import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ExportableData<T> {

//    @Async
//    void exportExcel(OutputStream outputStream, List<T> data) throws Exception;
    CompletableFuture<Void> exportExcel(OutputStream outputStream, List<T> data) throws Exception;

    void export(OutputStream outputStream, List<T> data);

    ExcelServiceAbstract initService(List<T> data);
}
