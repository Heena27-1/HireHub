package com.hirehub.hirehub_backend.controller;

import com.hirehub.hirehub_backend.dto.ApplicationRequest;
import com.hirehub.hirehub_backend.dto.ApplicationResponse;
import com.hirehub.hirehub_backend.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
public ApplicationResponse applyForJob(
        @Valid @RequestBody ApplicationRequest request) {

    System.out.println("Inside Application Controller");

    return applicationService.applyForJob(request);
}

    @GetMapping("/student/{studentId}")
    public List<ApplicationResponse> getApplicationsByStudent(
            @PathVariable Long studentId) {

        return applicationService.getApplicationsByStudent(studentId);
    }

    @GetMapping("/job/{jobId}")
    public List<ApplicationResponse> getApplicationsByJob(
            @PathVariable Long jobId) {

        return applicationService.getApplicationsByJob(jobId);
    }
    @GetMapping("/test")
public String test() {
    return "Application Controller Working";
}
}