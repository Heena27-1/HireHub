package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.RecruiterRequest;
import com.hirehub.hirehub_backend.dto.RecruiterResponse;
import com.hirehub.hirehub_backend.entity.Recruiter;
import com.hirehub.hirehub_backend.repository.RecruiterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public RecruiterResponse createRecruiter(RecruiterRequest request) {

        Recruiter recruiter = new Recruiter();

        recruiter.setFullName(request.getFullName());
        recruiter.setEmail(request.getEmail());
        recruiter.setPhone(request.getPhone());
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setDesignation(request.getDesignation());
        recruiter.setCreatedAt(LocalDateTime.now());

        Recruiter saved = recruiterRepository.save(recruiter);

        return mapToResponse(saved);
    }

    @Override
    public List<RecruiterResponse> getAllRecruiters() {

        return recruiterRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public RecruiterResponse getRecruiterById(Long id) {

        Recruiter recruiter = recruiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        return mapToResponse(recruiter);
    }

    @Override
    public RecruiterResponse updateRecruiter(Long id, RecruiterRequest request) {

        Recruiter recruiter = recruiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        recruiter.setFullName(request.getFullName());
        recruiter.setEmail(request.getEmail());
        recruiter.setPhone(request.getPhone());
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setDesignation(request.getDesignation());

        Recruiter updated = recruiterRepository.save(recruiter);

        return mapToResponse(updated);
    }

    @Override
    public void deleteRecruiter(Long id) {
        recruiterRepository.deleteById(id);
    }

    private RecruiterResponse mapToResponse(Recruiter recruiter) {

        return new RecruiterResponse(
                recruiter.getId(),
                recruiter.getFullName(),
                recruiter.getEmail(),
                recruiter.getPhone(),
                recruiter.getCompanyName(),
                recruiter.getDesignation(),
                recruiter.getCreatedAt()
        );
    }
}