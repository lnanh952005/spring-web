package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.annotation.ApiMessage;
import com.javaweb.buildingproject.domain.dto.JobDTO;
import com.javaweb.buildingproject.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @ApiMessage("fetch all jobs")
    @GetMapping("/jobs")
    public ResponseEntity<?> fetchAllJobs() {
        return ResponseEntity.ok().body(jobService.fetchAllJobs());
    }

    @ApiMessage("fetch by id")
    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> fetchById(@PathVariable Long id) {
        return ResponseEntity.ok().body(jobService.fetchById(id));
    }

    @ApiMessage("create a job")
    @PostMapping("/jobs")
    public ResponseEntity<?> createJob(@RequestBody JobDTO jobDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(jobDTO));
    }

    @ApiMessage("update job")
    @PutMapping("/jobs/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok().body(jobService.updateJob(id,jobDTO));
    }

    @ApiMessage("delete job")
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("delete job successfully");
    }
}
