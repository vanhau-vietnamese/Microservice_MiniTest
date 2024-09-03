package com.tranvanhau.jobms.job.impl;

import com.tranvanhau.jobms.job.Job;
import com.tranvanhau.jobms.job.JobRepository;
import com.tranvanhau.jobms.job.JobService;
import com.tranvanhau.jobms.job.clients.CompanyClient;
import com.tranvanhau.jobms.job.clients.ReviewClient;
import com.tranvanhau.jobms.job.dto.JobDTO;
import com.tranvanhau.jobms.job.external.Company;
import com.tranvanhau.jobms.job.external.Review;
import com.tranvanhau.jobms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;
    private final JobRepository jobRepository;

    int attempt = 0;
    @Autowired
    RestTemplate restTemplate;

//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyRetry", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyRatelimiter", fallbackMethod = "companyBreakerFallback")
    @Override
    public List<JobDTO> findAll() {
        System.out.println("Attempt: " + ++attempt);
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Van Hau");
        return list;
    }

    private JobDTO convertToDTO(Job job){
//        RestTemplate restTemplate = new RestTemplate();

        // cachs 1:
//        Company company = restTemplate.getForObject("http://COMPANYMS:8082/companies/" + job.getCompanyId(), Company.class);
        // cach 2: openfeign
        Company company = companyClient.getCompany(job.getCompanyId());

//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMS:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
//        });
//        List<Review> reviews = reviewResponse.getBody();
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        JobDTO jobWithCompanyDTO = JobMapper.mapJobWithCompanyDTO(job, company, reviews);
        return jobWithCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean updateJob(Long jobId, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);

            return true;
        }
        return false;
    }

    @Override
    public JobDTO getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public boolean deleteJob(Long jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            jobRepository.delete(job);
            return true;
        }
        return false;
    }

}
