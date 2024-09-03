package com.tranvanhau.companyms.company.messaging;

import com.tranvanhau.companyms.company.CompanyService;
import com.tranvanhau.companyms.company.dto.ReviewMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewMessageConsumer {
    private final CompanyService companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumerMessage(ReviewMessageDTO reviewMessageDTO) {
        companyService.updateCompanyRating(reviewMessageDTO);
    }
}
