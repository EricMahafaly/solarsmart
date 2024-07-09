package com.solarsmart.frontoffice.controllers;

import com.solarsmart.frontoffice.models.dto.request.FilterRequest;
import com.solarsmart.frontoffice.models.dto.request.PageBuilderRequest;
import com.solarsmart.frontoffice.models.dto.response.ApiResponse;
import com.solarsmart.frontoffice.models.dto.response.PageResponse;
import com.solarsmart.frontoffice.models.dto.response.RatingListResponse;
import com.solarsmart.frontoffice.models.entities.Rating;
import com.solarsmart.frontoffice.models.specifications.SpecificationBuilder;
import com.solarsmart.frontoffice.services.api.RatingService;
import com.solarsmart.frontoffice.services.api.base.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solar/ratings")
@RequiredArgsConstructor
@Slf4j
public class RatingController {

    private final RatingService ratingService;
    private final FilterService<Rating> ratingFilterService;

    @GetMapping("/avg")
    public ResponseEntity<?> getAverage(){
        double moyenne = this.ratingService.getNoteMoyenne();
        ApiResponse<?> response = ApiResponse.success()
                .body(moyenne)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getFiveLatest(){
        List<Rating> ratings = this.ratingService.getFiveLatest();
        log.info("retrieve five latest of ratings and comments: {}", ratings);

        List<RatingListResponse> ratingResponses = RatingListResponse.map(ratings);
        ApiResponse<?> response = ApiResponse.success()
                .body(ratingResponses)
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int pageSize,
            @RequestParam(required = false, name = "sortBy", defaultValue = "name") String sort,
            @RequestParam(required = false, name = "direction", defaultValue = "ASC") String direction){

        PageBuilderRequest request = new PageBuilderRequest();
        request.setDirection(direction);
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortBy(sort);

        Pageable pageable = request.build();

        Page<RatingListResponse> ratings = this.ratingService.list(pageable).map(RatingListResponse::new);

        PageResponse<RatingListResponse> pageResponse =
                new PageResponse<>(ratings.getContent(), ratings.getTotalElements(), ratings.getTotalPages());

        ApiResponse<?> response = ApiResponse.success()
                .body(pageResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> customersFilter(
            @RequestBody FilterRequest filterRequest,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int pageSize,
            @RequestParam(required = false, name = "sortBy", defaultValue = "name") String sort,
            @RequestParam(required = false, name = "direction", defaultValue = "ASC") String direction){

        PageBuilderRequest request = new PageBuilderRequest();
        request.setDirection(direction);
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortBy(sort);

        Pageable pageable = request.build();
        SpecificationBuilder<Rating> specBuilder = new SpecificationBuilder<>();
        filterRequest.getCriteria().forEach(
                searchCriteria -> {
                    specBuilder.with(searchCriteria);
                    searchCriteria.setDataOption(filterRequest.getDataOption());
                }
        );

        Page<RatingListResponse> ratings =
                this.ratingFilterService.filter(specBuilder.build(), pageable).map(RatingListResponse::new);

        PageResponse<RatingListResponse> pageResponse =
                new PageResponse<>(ratings.getContent(), ratings.getTotalElements(), ratings.getTotalPages());

        ApiResponse<?> response = ApiResponse.success()
                .body(pageResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
