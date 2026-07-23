package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.JobRequest;
import com.hirehub.hirehub_backend.dto.JobResponse;

import java.util.List;

public interface JobService {

    JobResponse createJob(JobRequest request);

    List<JobResponse> getAllJobs();

    JobResponse getJobById(Long id);

    void deleteJob(Long id);
}