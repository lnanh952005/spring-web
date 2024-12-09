package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.annotation.ApiMessage;
import com.javaweb.buildingproject.domain.dto.JobDTO;
import com.javaweb.buildingproject.service.JobService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> fetchAllJobs(@RequestParam(defaultValue = "1")Integer pageNumber
                                        , @RequestParam(defaultValue = "10")Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return ResponseEntity.ok().body(jobService.fetchAllJobs(pageable));
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

    @ApiMessage("update a job")
    @PutMapping("/jobs/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok().body(jobService.updateJob(id,jobDTO));
    }

    @ApiMessage("delete a job")
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
