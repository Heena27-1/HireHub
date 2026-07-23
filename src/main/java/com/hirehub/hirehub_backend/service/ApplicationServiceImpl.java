package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.ApplicationRequest;
import com.hirehub.hirehub_backend.dto.ApplicationResponse;
import com.hirehub.hirehub_backend.entity.Application;
import com.hirehub.hirehub_backend.entity.ApplicationStatus;
import com.hirehub.hirehub_backend.entity.Job;
import com.hirehub.hirehub_backend.entity.User;
import com.hirehub.hirehub_backend.repository.ApplicationRepository;
import com.hirehub.hirehub_backend.repository.JobRepository;
import com.hirehub.hirehub_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            JobRepository jobRepository,
            UserRepository userRepository) {

        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApplicationResponse applyForJob(ApplicationRequest request) {

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
                Optional<Application> existingApplication =
        applicationRepository.findByStudentIdAndJobId(
                student.getId(),
                job.getId());

if (existingApplication.isPresent()) {
    throw new RuntimeException("You have already applied for this job.");
}

        Application application = new Application();

        application.setJob(job);
        application.setStudent(student);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setAppliedAt(LocalDateTime.now());

        Application saved = applicationRepository.save(application);

        return mapToResponse(saved);
    }

    @Override
    public List<ApplicationResponse> getApplicationsByStudent(Long studentId) {

        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsByJob(Long jobId) {

        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ApplicationResponse mapToResponse(Application application) {

        return new ApplicationResponse(
                application.getId(),
                application.getJob().getId(),
                application.getJob().getTitle(),
                application.getStudent().getId(),
                application.getStudent().getFullName(),
                application.getStatus(),
                application.getAppliedAt()
        );
    }
}