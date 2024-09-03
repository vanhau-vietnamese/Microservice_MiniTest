package com.tranvanhau.firstjobapp.job.impl;

import com.tranvanhau.firstjobapp.company.Company;
import com.tranvanhau.firstjobapp.company.CompanyRepository;
import com.tranvanhau.firstjobapp.job.Job;
import com.tranvanhau.firstjobapp.job.JobRepository;
import com.tranvanhau.firstjobapp.job.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
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
    public Job getJobById(Long jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        return jobOptional.orElse(null);
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
