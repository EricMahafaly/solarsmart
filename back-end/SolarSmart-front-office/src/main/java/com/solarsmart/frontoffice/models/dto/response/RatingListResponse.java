package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.Rating}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingListResponse implements Serializable {
    private Long id;
    private Integer score;
    private String comment;
    private LocalDateTime date;

    private Long customerId;
    private String customerName;
//    private String customerLastName;
    private String customerImage;

    public RatingListResponse(Rating rating) {
        Customer customer = rating.getCustomer();

        this.id = rating.getId();
        this.score = rating.getScore();
        this.comment = rating.getComment();
        this.date = rating.getDate();

        this.customerId = customer.getId();
        this.customerName = customer.getName() + " " + customer.getLastName();
        this.customerImage = customer.getImage();
    }

    public static List<RatingListResponse> map(List<Rating> ratings){
        return ratings.stream().map(RatingListResponse::new)
                .collect(Collectors.toList());
    }
}