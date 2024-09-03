package com.tranvanhau.jobms.job.mapper;

import com.tranvanhau.jobms.job.Job;
import com.tranvanhau.jobms.job.dto.JobDTO;
import com.tranvanhau.jobms.job.external.Company;
import com.tranvanhau.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapJobWithCompanyDTO(Job job, Company company, List<Review> reviews) {
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReviews(reviews);
        return jobWithCompanyDTO;
    }
}
