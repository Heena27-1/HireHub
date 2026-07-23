package com.hirehub.hirehub_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequest {

    @NotNull
    private Long jobId;

    @NotNull
    private Long studentId;
}