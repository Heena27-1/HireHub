package com.hirehub.hirehub_backend.controller;

import com.hirehub.hirehub_backend.dto.RecruiterRequest;
import com.hirehub.hirehub_backend.dto.RecruiterResponse;
import com.hirehub.hirehub_backend.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @PostMapping
    public RecruiterResponse createRecruiter(
            @Valid @RequestBody RecruiterRequest request) {

        return recruiterService.createRecruiter(request);
    }

    @GetMapping
    public List<RecruiterResponse> getAllRecruiters() {

        return recruiterService.getAllRecruiters();
    }

    @GetMapping("/{id}")
    public RecruiterResponse getRecruiterById(@PathVariable Long id) {

        return recruiterService.getRecruiterById(id);
    }

    @PutMapping("/{id}")
    public RecruiterResponse updateRecruiter(
            @PathVariable Long id,
            @Valid @RequestBody RecruiterRequest request) {

        return recruiterService.updateRecruiter(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteRecruiter(@PathVariable Long id) {

        recruiterService.deleteRecruiter(id);

        return "Recruiter deleted successfully";
    }
}