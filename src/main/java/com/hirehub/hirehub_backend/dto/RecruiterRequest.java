package com.hirehub.hirehub_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email
    private String email;

    private String phone;

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String designation;
}