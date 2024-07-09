package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Reference;
import com.solarsmart.frontoffice.models.entities.ReferencePrise;

public interface ReferenceService<T extends Reference> {

    T toDefault(Long moduleId);

    T findByModuleId(Long moduleId);
}
