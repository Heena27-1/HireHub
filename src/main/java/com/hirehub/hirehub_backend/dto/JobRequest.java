package com.hirehub.hirehub_backend.dto;

import com.hirehub.hirehub_backend.entity.JobType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String company;

    private String location;

    private Double salary;

    private String description;

    private String skills;

    private JobType jobType;
}