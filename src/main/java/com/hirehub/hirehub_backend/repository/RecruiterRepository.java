package com.hirehub.hirehub_backend.repository;

import com.hirehub.hirehub_backend.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

}