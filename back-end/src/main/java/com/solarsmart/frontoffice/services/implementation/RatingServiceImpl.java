package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.Rating;
import com.solarsmart.frontoffice.repositories.api.RatingRepository;
import com.solarsmart.frontoffice.services.api.RatingService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl extends BaseServiceImpl<Rating, RatingRepository> implements RatingService {

    public RatingServiceImpl(RatingRepository repository) {
        super(repository);
    }

    @Override
    public double getNoteMoyenne() {
        return this.repository.averageScore();
    }

    @Override
    public List<Rating> getFiveLatest() {

        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        PageRequest pageRequest = PageRequest.of(0, 5, sort);

        Page<Rating> ratingPage = this.list(pageRequest);

        return ratingPage.getContent();
    }
}
