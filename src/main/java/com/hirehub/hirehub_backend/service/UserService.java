package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.RegisterRequest;
import com.hirehub.hirehub_backend.dto.UserResponse;
import com.hirehub.hirehub_backend.dto.LoginRequest;
import com.hirehub.hirehub_backend.dto.LoginResponse;
import java.util.List;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);
    LoginResponse login(LoginRequest request);

    void deleteUser(Long id);
}