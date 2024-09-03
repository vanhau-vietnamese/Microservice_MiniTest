package com.tranvanhau.companyms.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEW-SERVICE", url = "${review-service.url}")
public interface ReviewClients {
    @GetMapping("/reviews/averageRating")
    public Double getAverageRatingForCompany(@RequestParam("companyId") Long companyId) ;
}
