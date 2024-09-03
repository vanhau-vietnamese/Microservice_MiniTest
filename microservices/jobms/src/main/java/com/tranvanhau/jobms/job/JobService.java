package com.tranvanhau.jobms.job;

import com.tranvanhau.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);
    boolean updateJob(Long jobId, Job job);
    JobDTO getJobById(Long jobId);
    boolean deleteJob(Long jobId);
}
