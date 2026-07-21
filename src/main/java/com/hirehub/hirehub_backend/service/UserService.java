package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

}