package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.ApplicationRequest;
import com.hirehub.hirehub_backend.dto.ApplicationResponse;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse applyForJob(ApplicationRequest request);

    List<ApplicationResponse> getApplicationsByStudent(Long studentId);

    List<ApplicationResponse> getApplicationsByJob(Long jobId);
}