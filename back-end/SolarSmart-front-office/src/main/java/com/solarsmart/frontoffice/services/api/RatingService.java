package com.solarsmart.frontoffice.services.api;

import com.solarsmart.frontoffice.models.entities.Rating;
import com.solarsmart.frontoffice.services.api.base.ReadingService;

import java.util.List;

public interface RatingService extends ReadingService<Rating> {

    double getNoteMoyenne();

    List<Rating> getFiveLatest();
}
