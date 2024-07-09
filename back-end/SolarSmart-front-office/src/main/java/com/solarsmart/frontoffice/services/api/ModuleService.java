package com.solarsmart.frontoffice.services.api;

import com.google.zxing.WriterException;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.services.api.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.OutputStream;

public interface ModuleService extends BaseService<ModuleSolar> {

    Page<ModuleSolar> getModulesUnused(Pageable pageable);

    void toQrCode(long id, OutputStream stream) throws IOException, WriterException;
}
