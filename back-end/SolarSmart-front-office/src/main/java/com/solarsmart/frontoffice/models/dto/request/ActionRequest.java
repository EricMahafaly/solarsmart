package com.solarsmart.frontoffice.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.Action}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionRequest implements Serializable {
    private String type;
}