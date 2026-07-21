package com.hirehub.hirehub_backend.repository;

import com.hirehub.hirehub_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}