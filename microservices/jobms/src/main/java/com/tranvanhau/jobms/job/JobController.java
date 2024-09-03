package com.tranvanhau.jobms.job;

import com.tranvanhau.jobms.job.dto.JobDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.OK);
    }
    @PutMapping("/update/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable("jobId") Long jobId,@RequestBody Job job){
        boolean isJob = jobService.updateJob(jobId, job);
        if(isJob){
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found job to update", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<?> getJobById(@PathVariable("jobId")Long jobId){
        JobDTO jobWithCompanyDTO = jobService.getJobById(jobId);
        if(jobWithCompanyDTO != null){
            return new ResponseEntity<>(jobWithCompanyDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable("jobId")Long jobId){
        boolean isDeletedJob = jobService.deleteJob(jobId);
        if(isDeletedJob){
            return new ResponseEntity<>("Deleted Job Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Job", HttpStatus.NOT_FOUND);
    }
}
