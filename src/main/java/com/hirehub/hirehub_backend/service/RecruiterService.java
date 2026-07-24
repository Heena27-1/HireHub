package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.RecruiterRequest;
import com.hirehub.hirehub_backend.dto.RecruiterResponse;

import java.util.List;

public interface RecruiterService {

    RecruiterResponse createRecruiter(RecruiterRequest request);

    List<RecruiterResponse> getAllRecruiters();

    RecruiterResponse getRecruiterById(Long id);

    RecruiterResponse updateRecruiter(Long id, RecruiterRequest request);

    void deleteRecruiter(Long id);
}