package com.hirehub.hirehub_backend.dto;

import com.hirehub.hirehub_backend.entity.ApplicationStatus;

import java.time.LocalDateTime;

public class ApplicationResponse {

    private Long id;
    private Long jobId;
    private String jobTitle;
    private Long studentId;
    private String studentName;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;

    public ApplicationResponse(
            Long id,
            Long jobId,
            String jobTitle,
            Long studentId,
            String studentName,
            ApplicationStatus status,
            LocalDateTime appliedAt) {

        this.id = id;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.studentId = studentId;
        this.studentName = studentName;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }
}