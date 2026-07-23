package com.hirehub.hirehub_backend.repository;

import com.hirehub.hirehub_backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}