package com.solarsmart.frontoffice.repositories.api;

import com.solarsmart.frontoffice.models.entities.Rating;
import com.solarsmart.frontoffice.repositories.base.BaseRepository;

public interface RatingRepository extends BaseRepository<Rating> {

    double averageScore();
}
