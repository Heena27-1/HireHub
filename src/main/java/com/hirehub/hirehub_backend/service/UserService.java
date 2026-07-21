package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.RegisterRequest;
import com.hirehub.hirehub_backend.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    void deleteUser(Long id);
}