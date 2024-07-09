package com.solarsmart.frontoffice.models.entities.projection;

/**
 * Projection for {@link com.solarsmart.frontoffice.models.entities.Customer}
 */
public interface CustomerCount {
    Long getCount();

    Integer getMonth();

    Long getYear();
}