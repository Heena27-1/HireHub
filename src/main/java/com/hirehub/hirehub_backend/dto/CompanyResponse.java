package com.hirehub.hirehub_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CompanyResponse {

    private Long id;

    private String name;

    private String description;

    private String website;

    private String location;

    private LocalDateTime createdAt;
}