package com.hirehub.hirehub_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RecruiterResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String companyName;

    private String designation;

    private LocalDateTime createdAt;
}