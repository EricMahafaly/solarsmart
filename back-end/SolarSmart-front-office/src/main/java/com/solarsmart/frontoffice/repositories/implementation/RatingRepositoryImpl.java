package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Rating;
import com.solarsmart.frontoffice.repositories.api.RatingRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaRatingRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RatingRepositoryImpl extends BaseRepositoryImpl<Rating, JpaRatingRepository> implements RatingRepository {
    public RatingRepositoryImpl(JpaRatingRepository repository) {
        super(repository);
    }

    @Override
    public double averageScore() {
        return this.repository.getAvgScore();
    }
}
