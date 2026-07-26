package com.hirehub.hirehub_backend.controller;

import com.hirehub.hirehub_backend.dto.JobRequest;
import com.hirehub.hirehub_backend.dto.JobResponse;
import com.hirehub.hirehub_backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public JobResponse createJob(@Valid @RequestBody JobRequest request) {
        return jobService.createJob(request);
    }

    @GetMapping
    public List<JobResponse> getAllJobs() {
        return jobService.getAllJobs();
    }
    @GetMapping("/search/title")
public List<JobResponse> searchByTitle(
        @RequestParam String title) {

    return jobService.searchByTitle(title);
}
@GetMapping("/search/location")
public List<JobResponse> searchByLocation(
        @RequestParam String location) {

    return jobService.searchByLocation(location);
}
@GetMapping("/search/skills")
public List<JobResponse> searchBySkills(
        @RequestParam String skills) {

    return jobService.searchBySkills(skills);
}

    @GetMapping("/{id}")
    public JobResponse getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully";
    }
}