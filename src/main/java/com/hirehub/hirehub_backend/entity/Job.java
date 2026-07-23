package com.hirehub.hirehub_backend.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String company;

    private String location;

    private Double salary;

    @Column(length = 2000)
    private String description;

    private String skills;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    private LocalDateTime createdAt;
}