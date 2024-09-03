package com.tranvanhau.reviewms.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewByCompanyId(Long companyId);
    Review findByIdAndCompanyId(Long reviewId, Long companyId);
}
