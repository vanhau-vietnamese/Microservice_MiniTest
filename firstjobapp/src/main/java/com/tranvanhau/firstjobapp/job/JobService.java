package com.tranvanhau.firstjobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    boolean updateJob(Long jobId, Job job);
    Job getJobById(Long jobId);
    boolean deleteJob(Long jobId);
}
