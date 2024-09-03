package com.tranvanhau.firstjobapp.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getReviewsByCompany_Id(Long companyId);
    Review findByIdAndCompanyId(Long reviewId, Long companyId);
}
