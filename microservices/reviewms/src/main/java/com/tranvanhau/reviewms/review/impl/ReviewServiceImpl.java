package com.tranvanhau.reviewms.review.impl;

import com.tranvanhau.reviewms.review.Review;
import com.tranvanhau.reviewms.review.ReviewRepository;
import com.tranvanhau.reviewms.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findReviewByCompanyId(companyId);
    }
    @Override
    public void createReview(Long companyId, Review review) {
        if(companyId != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review reviewUpdated) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            review.setTitle(reviewUpdated.getTitle());
            review.setDescription(reviewUpdated.getDescription());
            review.setRating(reviewUpdated.getRating());
            review.setCompanyId(reviewUpdated.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
