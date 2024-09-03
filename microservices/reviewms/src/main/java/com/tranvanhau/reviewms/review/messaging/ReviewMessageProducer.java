package com.tranvanhau.reviewms.review.messaging;

import com.tranvanhau.reviewms.review.Review;
import com.tranvanhau.reviewms.review.dto.ReviewMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(Review review) {
        ReviewMessageDTO reviewMessageDTO = new ReviewMessageDTO();
        reviewMessageDTO.setId(review.getId());
        reviewMessageDTO.setTitle(review.getTitle());
        reviewMessageDTO.setDescription(review.getDescription());
        reviewMessageDTO.setRating(review.getRating());
        reviewMessageDTO.setCompanyId(review.getCompanyId());
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessageDTO);
    }
}
