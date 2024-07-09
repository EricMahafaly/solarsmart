package com.solarsmart.frontoffice.repositories.jpa;

import com.solarsmart.frontoffice.models.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRatingRepository extends JpaRepository<Rating, Long>, JpaSpecificationExecutor<Rating> {

    @Query("select coalesce(avg(r.score), 0) from Rating r")
    double getAvgScore();
}