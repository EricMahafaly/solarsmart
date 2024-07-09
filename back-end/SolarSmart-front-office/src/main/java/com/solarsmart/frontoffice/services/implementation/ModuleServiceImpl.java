package com.solarsmart.frontoffice.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.solarsmart.frontoffice.models.base.QrCodeModel;
import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.ModuleSolar;
import com.solarsmart.frontoffice.models.entities.ReferenceBattery;
import com.solarsmart.frontoffice.models.entities.ReferencePrise;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.repositories.api.ModuleRepository;
import com.solarsmart.frontoffice.services.api.ModuleService;
import com.solarsmart.frontoffice.services.api.ReferenceBatteryService;
import com.solarsmart.frontoffice.services.api.ReferencePriseService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;

@Service
@Slf4j
public class ModuleServiceImpl extends BaseServiceImpl<ModuleSolar, ModuleRepository> implements ModuleService {

    public ModuleServiceImpl(ModuleRepository repository) {
        super(repository);
    }

    @Override
    public ModuleSolar get(Long id) {
        return repository.findById(id).orElseThrow(()-> new DomainException("le module recherché n'existe pas"));
    }

    @Override
    public Page<ModuleSolar> getModulesUnused(Pageable pageable) {
        return repository.findModulesUnused(pageable);
    }

    @Override
    public void toQrCode(long id, OutputStream stream) throws IOException, WriterException {
        ModuleSolar moduleSolar = this.get(id);

        ObjectMapper objectMapper = new ObjectMapper();

        QrCodeModel qrCodeModel = new QrCodeModel(moduleSolar);

        String moduleJson = objectMapper.writeValueAsString(qrCodeModel);

        log.info("qr code to json: {}", moduleJson);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(moduleJson, BarcodeFormat.QR_CODE, 400, 400);

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);
    }

    @Override
    public void delete(Long id) {
        ModuleSolar moduleSolar = this.get(id);

        Customer customer = moduleSolar.getCustomer();
        if (customer != null) throw new DomainException("This action cannot be performed because this module is used");

        super.delete(moduleSolar);
    }

    @Override
    @Transactional
    public ModuleSolar create(ModuleSolar entity) {
        ModuleSolar moduleSolar = super.create(entity);

        return moduleSolar;
    }
}
