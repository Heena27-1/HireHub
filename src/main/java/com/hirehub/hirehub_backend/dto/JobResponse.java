package com.hirehub.hirehub_backend.dto;

import com.hirehub.hirehub_backend.entity.JobType;

import java.time.LocalDateTime;

public class JobResponse {

    private Long id;
    private String title;
    private String company;
    private String location;
    private Double salary;
    private String description;
    private String skills;
    private JobType jobType;
    private LocalDateTime createdAt;

    public JobResponse(Long id,
                       String title,
                       String company,
                       String location,
                       Double salary,
                       String description,
                       String skills,
                       JobType jobType,
                       LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
        this.description = description;
        this.skills = skills;
        this.jobType = jobType;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public Double getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getSkills() {
        return skills;
    }

    public JobType getJobType() {
        return jobType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}