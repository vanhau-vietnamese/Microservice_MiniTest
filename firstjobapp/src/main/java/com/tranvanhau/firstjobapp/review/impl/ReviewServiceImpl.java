package com.tranvanhau.firstjobapp.review.impl;

import com.tranvanhau.firstjobapp.company.Company;
import com.tranvanhau.firstjobapp.company.CompanyService;
import com.tranvanhau.firstjobapp.job.Job;
import com.tranvanhau.firstjobapp.review.Review;
import com.tranvanhau.firstjobapp.review.ReviewRepository;
import com.tranvanhau.firstjobapp.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;
    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.getReviewsByCompany_Id(companyId);
    }
    @Override
    public void createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
        }
    }

    @Override
    public Review getReiview(Long companyId, Long reviewId) {
        return reviewRepository.findByIdAndCompanyId(reviewId, companyId);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review reviewUpdated) {
        Review review = reviewRepository.findByIdAndCompanyId(reviewId, companyId);
        if(review != null){
            reviewUpdated.setCompany(review.getCompany());
            reviewUpdated.setId(reviewId);
            reviewRepository.save(reviewUpdated);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Review review = reviewRepository.findByIdAndCompanyId(reviewId, companyId);
        if(review != null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
