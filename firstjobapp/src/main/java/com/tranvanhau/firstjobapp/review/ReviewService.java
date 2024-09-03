package com.tranvanhau.firstjobapp.review;

import com.tranvanhau.firstjobapp.company.Company;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    void createReview(Long companyId, Review review);
    Review getReiview(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
