package com.tranvanhau.firstjobapp.review;

import com.tranvanhau.firstjobapp.company.Company;
import com.tranvanhau.firstjobapp.company.CompanyRepository;
import com.tranvanhau.firstjobapp.job.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;
    private final CompanyRepository companyRepository;
    @GetMapping("/reviews")
    public ResponseEntity<?> getAllReviews(@PathVariable("companyId") Long companyId){
        if(companyRepository.existsById(companyId)){
            return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Company", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable("companyId")Long companyId, @RequestBody Review review){
        reviewService.createReview(companyId, review);
        return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable("companyId")Long companyId, @PathVariable("reviewId")Long reviewId){
        Review review = reviewService.getReiview(companyId, reviewId);
        if(review != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable("companyId")Long companyId, @PathVariable("reviewId") Long reviewId,@RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found Review to update", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("companyId")Long companyId,@PathVariable("reviewId")Long reviewId){
        boolean isDeletedReview = reviewService.deleteReview(companyId, reviewId);
        if(isDeletedReview){
            return new ResponseEntity<>("Deleted Review Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Review", HttpStatus.NOT_FOUND);
    }
}
