package com.tranvanhau.reviewms.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewMessageDTO {
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;
}
