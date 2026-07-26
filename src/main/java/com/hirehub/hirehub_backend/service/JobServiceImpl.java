package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.JobRequest;
import com.hirehub.hirehub_backend.dto.JobResponse;
import com.hirehub.hirehub_backend.entity.Job;
import com.hirehub.hirehub_backend.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public JobResponse createJob(JobRequest request) {

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setDescription(request.getDescription());
        job.setSkills(request.getSkills());
        job.setJobType(request.getJobType());
        job.setCreatedAt(LocalDateTime.now());

        Job savedJob = jobRepository.save(job);

        return mapToResponse(savedJob);
    }
    @Override
public List<JobResponse> getAllJobs() {

    return jobRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
}

@Override
public Page<JobResponse> getAllJobsPaged(int page, int size) {

    return jobRepository.findAll(PageRequest.of(page, size))
            .map(this::mapToResponse);
}
@Override
public List<JobResponse> getAllJobsSorted(String sortBy, String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    return jobRepository.findAll(sort)
            .stream()
            .map(this::mapToResponse)
            .toList();
}

    @Override
    public JobResponse getJobById(Long id) {

        Job job = jobRepository.findById(id).orElse(null);

        if (job == null) {
            return null;
        }

        return mapToResponse(job);
    }
    @Override
public List<JobResponse> searchByTitle(String title) {

    return jobRepository.findByTitleContainingIgnoreCase(title)
            .stream()
            .map(this::mapToResponse)
            .toList();
}
@Override
public List<JobResponse> searchByLocation(String location) {

    return jobRepository.findByLocationContainingIgnoreCase(location)
            .stream()
            .map(this::mapToResponse)
            .toList();
}
@Override
public List<JobResponse> searchBySkills(String skills) {

    return jobRepository.findBySkillsContainingIgnoreCase(skills)
            .stream()
            .map(this::mapToResponse)
            .toList();
}

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    private JobResponse mapToResponse(Job job) {

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                job.getLocation(),
                job.getSalary(),
                job.getDescription(),
                job.getSkills(),
                job.getJobType(),
                job.getCreatedAt()
        );
    }
}