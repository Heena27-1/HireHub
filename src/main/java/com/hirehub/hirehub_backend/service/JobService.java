package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.JobRequest;
import com.hirehub.hirehub_backend.dto.JobResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {

    JobResponse createJob(JobRequest request);

    // Existing API
    List<JobResponse> getAllJobs();

    // New Pagination API
    Page<JobResponse> getAllJobsPaged(int page, int size);
    List<JobResponse> getAllJobsSorted(String sortBy, String direction);

    JobResponse getJobById(Long id);

    List<JobResponse> searchByTitle(String title);

    List<JobResponse> searchByLocation(String location);

    List<JobResponse> searchBySkills(String skills);

    void deleteJob(Long id);
}